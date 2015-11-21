package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.mysql.jdbc.Statement;

import domain.Additional;
import domain.Address;
import domain.Client;
import domain.Collect;
import domain.Delivery;
import domain.Order;
import domain.Payment;
import domain.Product;
import domain.Receiving;

//Design pattern DAO
public class OrderDAO {
	private Connection con;

	public OrderDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	private int saveDeliveryAddress(Address a) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into address " +
		"(cep, address, addressComplement)" +
		" values (?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			// prepared statement for insertion
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			stmt.setString(1, a.getCep());
			stmt.setString(2, a.getAddress());
			stmt.setString(3, a.getComplement());
			
			int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating address failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				stmt.close();
	            if (generatedKeys.next()) {
	                return (int) generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating address failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}
	
	private Receiving getReceivingFromOrder(int addressId){
		this.con = new ConnectionFactory().getConnection();
		
		String address, cep, complement;
        String sql = "select * from address where idAddress=?";
        Receiving r = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, addressId);
            rs = stmt.executeQuery();

            if (rs.next()) {
				cep = rs.getString("cep");
				address = rs.getString("address");
				complement = rs.getString("addressComplement");
				
				Address add = new Address(addressId, cep, address, complement);
				
				if(add.equals(Collect.sushizukiLocation)){
					r = new Collect();
				} else {
					r = new Delivery(add);
				}
            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR SETTING RECEIVING METHOD: "+e.getMessage());
        } finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
        
        return r;
	}
	
	private int savePayment(Payment p) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into payment " +
		"(change, paymentType_id)" +
		" values (?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			// prepared statement for insertion
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			stmt.setString(1, p.getChange());
			stmt.setLong(2, p.getPaymentId(p.getPaymentType()));
			
			int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating payment failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				stmt.close();
	            if (generatedKeys.next()) {
	                return (int) generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating payment failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}
	
	public void insert(Order order) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into order " +
		"(client_user_iduser, deliveryTime, totalPrice, idDeliveryAdress, payment_idPayment)" +
		" values (?,?,?,?,?)";
		
		PreparedStatement stmt = null;		
		
		try {
			// prepared statement for insertion
			stmt = con.prepareStatement(sql);
			
			// set values for each '?'
			stmt.setLong(1, order.getClient().getId());
			//CONVERTER CALENDAR PARA DATETIME SQL
			stmt.setDate(2, (Date) order.getReceiving().getTime().getTime());
			stmt.setString(3, String.valueOf(order.getTotalPrice()));
			stmt.setLong(4, saveDeliveryAddress(order.getReceiving().getAddress()));
			stmt.setLong(5, savePayment(order.getPayment()));
			
			// execute
			stmt.execute();
			stmt.close();
			
			saveProductsToOrder(order);
			saveAdditionalsToOrder(order);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}	
	
	private void saveProductsToOrder(Order order) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into order_has_product " +
		"(order_idOrder, product_idProduct, extra_idExtra, quantity)" +
		" values (?,?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
		// prepared statement for insertion
		stmt = con.prepareStatement(sql);
		
		for (HashMap.Entry<Product, Integer> entry : order.getItems().entrySet()) {
			Product p = entry.getKey();
			int qtd = entry.getValue();
			stmt.setLong(1, order.getId());
			stmt.setLong(2, p.getId());
			stmt.setLong(3, p.getExtraId(p.getExtra()));
			stmt.setLong(4, qtd);
			
			// execute
			stmt.execute();
		}
		
		stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}	
	
	private void saveAdditionalsToOrder(Order order) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into order_has_additional " +
		"(order_idOrder, additional_idAdditional)" +
		" values (?,?)";
		
		PreparedStatement stmt = null;
		
		try {
		// prepared statement for insertion
		stmt = con.prepareStatement(sql);
		
		for(Additional a : order.getAdditionals()){
			stmt.setLong(1, order.getId());
			stmt.setLong(2, a.getId());
			
			// execute
			stmt.execute();
		}
		
		stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}
	
	private HashMap<Product,Integer> setExtraFromProductsInOrder(HashMap<Product,Integer> mapProductList, Order order) throws SQLException{
		this.con = new ConnectionFactory().getConnection();
		
		for(Product p : mapProductList.keySet()){
			String sql = "select extra_idExtra from order_has_product "
					+ "where order_idOrder=? and product_idProduct=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try { 
				stmt = con.prepareStatement(sql);   
				stmt.setInt(1, order.getId());
				stmt.setInt(2, p.getId());
				rs = stmt.executeQuery();
				if (rs.next()) {
					p.setExtra(rs.getInt("extra_idExtra"));
				}
				
				rs.close();
				stmt.close();
	
			}catch (Exception e) {
				throw new RuntimeException("ERROR ASSIGNING EXTRAS FOR PRODUCTS OF AN ORDER: "+e.getMessage());
			}finally {
				try {
					if(this.con != null) {
						con.close();
					}
					if(stmt != null) {
						stmt.close();
					}
					if(rs != null){
						rs.close();
					}
				} catch(SQLException e){}
	        }
		}

		return mapProductList; 
	}
	
	public HashMap<Product, Integer> getProductsFromOrder(Order order) throws SQLException{
		this.con = new ConnectionFactory().getConnection();
		
		ProductDAO prodDao = new ProductDAO();
		HashMap<Product,Integer> map = new HashMap<Product,Integer>();
		
		String sql = "select product_idProduct, quantity from order_has_product "
				+ "where order_idOrder=? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try { 
			stmt = con.prepareStatement(sql);   
			stmt.setInt(1, order.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {       
				Product p = prodDao.getProductById(rs.getInt("product_idProduct"));
				map.put(p, rs.getInt("quantity"));
			}
			
			rs.close();
			stmt.close();
			map = setExtraFromProductsInOrder(map, order);
			
			return map; 

		}catch (Exception e) {
			throw new RuntimeException("ERROR RECOVERING PRODUCTS FROM ORDER: "+e.getMessage());
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
	}
	
	private List<Additional> assignAdditionalsToOrder(Order order) throws SQLException{
		this.con = new ConnectionFactory().getConnection();
		
		List<Additional> list = new ArrayList<Additional>();
		
		String sql = "select o_a.additional_idadditional, a.name from order_has_additional o_a, additional a "
				+ "where order_idOrder=? and a.idadditional=o_a.additional_idadditional";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try { 
			stmt = con.prepareStatement(sql);   
			stmt.setInt(1, order.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {       
				Additional a = new Additional();
				a.setId(rs.getInt("additional_idadditional"));
				a.setName(rs.getString("name"));
				list.add(a);
			}
			
			rs.close();
			stmt.close();
			
			return list; 

		}catch (Exception e) {
			throw new RuntimeException("ERROR RECOVERING ADDITIONALS FROM ORDER: "+e.getMessage());
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
	}
	
	public List<Order> getList() throws SQLException {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM `order`";

		
		PreparedStatement stmt = con.prepareStatement(sql);
		List<Order> orderList = new ArrayList<Order>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       
			UserDAO userDao = new UserDAO();

			while (rs.next()) { 
				Order order = new Order();
				
				order.setId(Integer.parseInt(rs.getString("idOrder")));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				order.setReceiving(getReceivingFromOrder((rs.getInt("idDeliveryAddress"))));
				order.setPayment(getPaymentFromOrder(rs.getInt("payment_idPayment")));
				
				//SET TIME OF RECEIVING
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
				cal.setTime(sdf.parse(rs.getString("deliveryTime")));
				order.getReceiving().setTime(cal);

				
				//ASSIGN PRODUCTS TO ORDER
				order.setItems(this.getProductsFromOrder(order));
			
				//ASSIGN ADDITIONALS TO ORDER
				order.setAdditionals(this.assignAdditionalsToOrder(order));
				
				//ASSIGN CLIENT
				order.setClient((Client) userDao.getUserById(rs.getInt("client_user_iduser")));
				
				orderList.add(order);
			}
			
			rs.close();
			stmt.close();
			return orderList; 

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
		
		return null;
   }
	
	public List<Additional> getAdditionalsList() {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM `additional`";

		
		List<Additional> list = new ArrayList<Additional>(); 

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try { 
			stmt = con.prepareStatement(sql);
	    	   
			rs = stmt.executeQuery(sql);     

			while (rs.next()) { 
				Additional additional = new Additional();
				
				additional.setId(Integer.parseInt(rs.getString("idadditional")));
				additional.setName(rs.getString("name"));
				
				list.add(additional);			
			}
			
			rs.close();
			stmt.close();
			return list; 

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
		
		return null;
   }
	
	public Additional getAdditionalById(int idAdditional) {
		this.con = new ConnectionFactory().getConnection();
		
        Additional additional = new Additional();
        String sql = "select * from additional where idadditional=?";
        PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAdditional);
            rs = stmt.executeQuery();

            if (rs.next()) {
				
            	additional.setId(Integer.parseInt(rs.getString("idadditional")));
				additional.setName(rs.getString("name"));

            }
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING ADDITIONAL: "+e.getMessage());
        } finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
        
        return additional;
    }
	
	private Payment getPaymentFromOrder(int idPayment) {
		this.con = new ConnectionFactory().getConnection();
		
		String paymentType, change;
        String sql = "select * from payment where idpayment=?";
        Payment p = null;
        PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPayment);
            rs = stmt.executeQuery();

            if (rs.next()) {
				change = rs.getString("change");
				paymentType = rs.getString("paymentType_id");
				
				p = new Payment(idPayment, paymentType, change);
            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR RETRIEVING PAYMENT FROM ORDER: "+e.getMessage());
        } finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
        
        return p;
	}

	public Order getOrderById(int orderId) throws ParseException {
		this.con = new ConnectionFactory().getConnection();
		
        Order order = new Order();
		UserDAO userDao = new UserDAO();
        String sql = "select * from order where idProduct=?";
        PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
				
				order.setId(Integer.parseInt(rs.getString("idOrder")));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				order.setReceiving(getReceivingFromOrder((rs.getInt("idDeliveryAddress"))));
				
				//SET TIME OF RECEIVING
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
				cal.setTime(sdf.parse(rs.getString("deliveryTime")));
				order.getReceiving().setTime(cal);

				
				//ASSIGN PRODUCTS TO ORDER
				order.setItems(this.getProductsFromOrder(order));
			
				//ASSIGN ADDITIONALS TO ORDER
				order.setAdditionals(this.assignAdditionalsToOrder(order));
				
				//ASSIGN CLIENT
				order.setClient((Client) userDao.getUserById(rs.getInt("client_user_iduser")));

            }
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING ORDER: "+e.getMessage());
        } finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
        
        return order;
    }
			
	public void delete(Order order) {
		String sql = "delete from order where idOrder=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, order.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}
	
	public List<String> getPaymentTypes() throws SQLException{
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "select payment from payment_type";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<String> paymentTypes = new ArrayList<String>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 			
				paymentTypes.add(rs.getString("payment"));
			}
			
			rs.close();
			stmt.close();
			return paymentTypes; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
	}
}
