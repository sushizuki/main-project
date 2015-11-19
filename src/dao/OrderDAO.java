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
import java.util.Map;

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
	
	private int saveDeliveryAddress(Order order) {
		String sql = "insert into address " +
		"(cep, address, addressComplement)" +
		" values (?,?,?)";
		try {
			// prepared statement for insertion
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			stmt.setString(1, order.getReceiving().getAddress().getCep());
			stmt.setString(2, order.getReceiving().getAddress().getAddress());
			stmt.setString(3, order.getReceiving().getAddress().getComplement());
			
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
		}
	}
	
	private Receiving getReceivingFromOrder(int addressId){
		String address, cep, complement;
        String sql = "select * from address where idAddress=?";
        Receiving r = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, addressId);
            ResultSet rs = preparedStatement.executeQuery();

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
            
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR SETTING RECEIVING METHOD: "+e.getMessage());
        }
        
        return r;
	}
	
	private int savePayment(Order order) {
		String sql = "insert into payment " +
		"(change, paymentType_id)" +
		" values (?,?)";
		try {
			// prepared statement for insertion
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			stmt.setString(1, order.getPayment().getChange());
			stmt.setLong(2, order.getPayment().getPaymentId(order.getPayment().getPaymentType()));
			
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
		}
	}
	
	public void insert(Order order) {
		String sql = "insert into order " +
		"(client_user_iduser, deliveryTime, totalPrice, idDeliveryAdress, payment_idPayment)" +
		" values (?,?,?,?,?)";
		
				
		try {
			// prepared statement for insertion
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// set values for each '?'
			stmt.setLong(1, order.getClient().getId());
			//CONVERTER CALENDAR PARA DATETIME SQL
			stmt.setDate(2, (Date) order.getReceiving().getTime().getTime());
			stmt.setString(3, String.valueOf(order.getTotalPrice()));
			stmt.setLong(4, saveDeliveryAddress(order));
			stmt.setLong(5, savePayment(order));
			
			// execute
			stmt.execute();
			stmt.close();
			
			saveProductsToOrder(order);
			saveAdditionalsToOrder(order);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	private void saveProductsToOrder(Order order) {
		String sql = "insert into order_has_product " +
		"(order_idOrder, product_idProduct, extra_idExtra, quantity)" +
		" values (?,?,?,?)";
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
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
		}
	}	
	
	private void saveAdditionalsToOrder(Order order) {
		String sql = "insert into order_has_additional " +
		"(order_idOrder, additional_idAdditional)" +
		" values (?,?)";
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
		for(Additional a : order.getAdditionals()){
			stmt.setLong(1, order.getId());
			stmt.setLong(2, a.getId());
			
			// execute
			stmt.execute();
		}
		
		stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private HashMap<Product,Integer> setExtraFromProductsInOrder(HashMap<Product,Integer> mapProductList, Order order) throws SQLException{
		for(Product p : mapProductList.keySet()){
			String sql = "select extra_idExtra from order_has_product "
					+ "where order_idOrder=? and product_idProduct=?";
						
			try { 
				PreparedStatement stmt = con.prepareStatement(sql);   
				stmt.setInt(1, order.getId());
				stmt.setInt(2, p.getId());
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					p.setExtra(rs.getInt("extra_idExtra"));
				}
				
				rs.close();
				stmt.close();
	
			}catch (Exception e) {
				throw new RuntimeException("ERROR ASSIGNING EXTRAS FOR PRODUCTS OF AN ORDER: "+e.getMessage());
			}
		}

		return mapProductList; 
	}
	
	public HashMap<Product, Integer> getProductsFromOrder(Order order) throws SQLException{
		
		ProductDAO prodDao = new ProductDAO();
		HashMap<Product,Integer> map = new HashMap<Product,Integer>();
		
		String sql = "select product_idProduct, quantity from order_has_product "
				+ "where order_idOrder=? ";
				
		try { 
			PreparedStatement stmt = con.prepareStatement(sql);   
			stmt.setInt(1, order.getId());
			ResultSet rs = stmt.executeQuery();
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
		}
	}
	
	private List<Additional> assignAdditionalsToOrder(Order order) throws SQLException{
		List<Additional> list = new ArrayList<Additional>();
		
		String sql = "select o_a.additional_idadditional, a.name from order_has_additional o_a, additional a "
				+ "where order_idOrder=? and a.idadditional=o_a.additional_idadditional";
		
		
		try { 
			PreparedStatement stmt = con.prepareStatement(sql);   
			stmt.setInt(1, order.getId());
			ResultSet rs = stmt.executeQuery();
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
		}
	}
	
	public List<Order> getList() throws SQLException {
		
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
		}
		return null;
   }
	
	private Payment getPaymentFromOrder(int idPayment) {
		String paymentType, change;
        String sql = "select * from payment where idpayment=?";
        Payment p = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idPayment);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
				change = rs.getString("change");
				paymentType = rs.getString("paymentType_id");
				
				p = new Payment(idPayment, paymentType, change);
            }
            
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR RETRIEVING PAYMENT FROM ORDER: "+e.getMessage());
        }
        
        return p;
	}

	public Order getOrderById(int orderId) throws ParseException {
        Order order = new Order();
		UserDAO userDao = new UserDAO();
        String sql = "select * from order where idProduct=?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();

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
        }
        
        return order;
    }
	
	public void update(Order order) {
		
	}
		
	public void delete(Order order) {
		String sql = "delete from order where idOrder=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, order.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public void finalize() {
		try {
			if(!this.con.isClosed()){
				this.con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR, could not close connection: "+e.getMessage());
		}
	}

	public List<String> getPaymentTypes() throws SQLException{
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
		}
	}
}
