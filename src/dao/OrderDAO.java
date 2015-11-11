package dao;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import domain.Additional;
import domain.Order;
import domain.Product;

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
	
	private int savePayment(Order order) {
		String sql = "insert into payment " +
		"(change, paymentType_id)" +
		" values (?,?)";
		try {
			// prepared statement for insertion
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			stmt.setString(1, order.getPayment().getChange());
			stmt.setLong(2, order.getPayment().getPaymentId(order.getPayment().getName()));
			
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
		"(order_idOrder, product_idProduct, extra_idExtra)" +
		" values (?,?,?)";
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
		for(Product p : order.getItems()){
			stmt.setLong(1, order.getId());
			stmt.setLong(2, p.getId());
			stmt.setLong(3, p.getExtraId(p.getExtra()));
			
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
	
	private List<Product> setExtraFromProductsInOrder(List<Product> productList, Order order) throws SQLException{
		for(Product p : productList){
			String sql = "select extra_idExtra from order_has_product where order_idOrder=? and product_idProduct=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, order.getId());
			stmt.setInt(1, p.getId());
			
			ResultSet rs = null;
			
			try { 
		    	   
				rs = stmt.executeQuery(sql); 
				if (rs.next()) {
					p.setExtra(rs.getInt("extra_idExtra"));
				}
				
				rs.close();
				stmt.close();
	
			}catch (Exception e) {
				throw new RuntimeException("ERROR ASSIGNING EXTRAS FOR PRODUCTS OF AN ORDER: "+e.getMessage());
			}
		}

		return productList; 
	}
	
	public List<Product> getProductsFromOrder(Order order) throws SQLException{
		
		ProductDAO prodDao = new ProductDAO();
		List<Product> list = new ArrayList<Product>();
		
		String sql = "select product_idProduct from order_has_product where order_idOrder=?";
				
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, order.getId());
		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql); 
			while (rs.next()) {       
				Product p = prodDao.getProductById(rs.getInt("product_idProduct"));
				list.add(p);
			}
			
			rs.close();
			stmt.close();
			list = setExtraFromProductsInOrder(list, order);
			
			return list; 

		}catch (Exception e) {
			throw new RuntimeException("ERROR RECOVERING PRODUCTS FROM ORDER: "+e.getMessage());
		}
	}
	
	public List<Order> getList() throws SQLException {
		
		String sql = "SELECT * FROM order";

		
		PreparedStatement stmt = con.prepareStatement(sql);
		List<Order> orderList = new ArrayList<Order>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 
				
				order.setId(Integer.parseInt(rs.getString("idOrder")));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				
				order.setDescription(rs.getString("description"));
				order.setPrice(Double.parseDouble(rs.getString("price")));
				order.setImgUrl(rs.getString("image"));
				order.setCategory(rs.getString("Category_idCategory"));
				

				Order order = new Order();
				
				//ASSIGN PRODUCTS TO ORDER
				order.setItems(getProductsFromOrder(order));
			
				//ASSIGN ADDITIONALS TO ORDER
				
				
				//ASSIGN CLIENT
				orderList.add(order);
			}
			
			rs.close();
			stmt.close();
			return orderList; 

		}catch (Exception e) {
			throw new RuntimeException("ERROR LISTING ORDERS: "+e.getMessage());
		}
   }
	
	public Order getOrderById(int orderId) {
        Order order = new Order();
        String sql = "select * from order where idProduct=?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	order.setId(Integer.parseInt(rs.getString("idproduct")));
            	order.setName(rs.getString("name"));
            	order.setDescription(rs.getString("description"));
            	order.setPrice(Double.parseDouble(rs.getString("price")));
            	order.setImgUrl(rs.getString("image"));
            	order.setCategory(rs.getString("Category_idCategory"));

            }
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING ORDER: "+e.getMessage());
        }
        
        return order;
    }
	
	public void update(Order order) {
		String sql = "update order set name=?, description=?, price=?," +
		" Category_idCategory=? where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, order.getName());
			stmt.setString(2, order.getDescription());
			stmt.setDouble(3, order.getPrice());
			stmt.setString(4, order.getCategory());
			stmt.setInt(5, order.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
		String sql = "select name from payment";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<String> paymentTypes = new ArrayList<String>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 			
				paymentTypes.add(rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
			return paymentTypes; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
