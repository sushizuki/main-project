/** 
*    OrderDAO.java to define OrderDAO 
*    {purpose} 
*/ 

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
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
public class OrderDAO extends DataAccessObject{	
	
	private String sqlQuery;	

	public OrderDAO() {	
		super();
	}
	
	private Timestamp castCalendarToDateTimeSQL(Calendar calendar){
		Timestamp sqlDate = new Timestamp(calendar.getTime().getTime());
		return sqlDate;
	}
	
	private Calendar castStringToCalendar(String date){
		Calendar time = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		try {
			time.setTime(dateFormat.parse(date));
		} catch (ParseException exception) {
			throw new RuntimeException("Error parsing time of receiving - castStringToCalendar in OrderDAO: "
					+exception.getMessage());
		}
		return time;
	}
	
	private int getDeliveryAddressId(Address deliveryAddress) {		
		AddressDAO addressDao = new AddressDAO();
		int savedAddressId = 0;
		
		Address savedAddress = addressDao.getAddress(deliveryAddress.getAddress());
		
		//if deliveryAddress was not in the database, adds it
		if(savedAddress == null){
			savedAddressId = addressDao.insert(deliveryAddress);
		} else {
			savedAddressId = savedAddress.getId();
		}
		
		return savedAddressId;
	}
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private Receiving getReceivingFromOrder(int addressId) throws SQLException{				
		String addressDescription = null;
		String cep = null;
		String complement = null;
        String sqlQuery = "select * from address where idAddress=?";
        Receiving receivingMethod = null;

        PreparedStatement statement = null;
		ResultSet result = null;
        
        try {
            statement = this.connection.prepareStatement(sqlQuery);
            
            // Replace '?' characters from sql variable into statement
            statement.setInt(1, addressId);
            
            result = statement.executeQuery();

            //If SQL query returned result
            if (result.next()) {
				cep = result.getString("cep");
				addressDescription = result.getString("address");
				complement = result.getString("addressComplement");
				
				Address address = new Address(addressId, cep, addressDescription, complement);
				
				if(address.getAddress().equals(Collect.SUSHIZUKI_LOCATION.getAddress())){
					receivingMethod = new Collect();
				} else {
					receivingMethod = new Delivery(address);
				}
            } else {
            	//nothing to do
            }
        } catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getReceivingFromOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
        
        return receivingMethod;
	}
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private int savePayment(Payment payment) throws SQLException {		
		String sqlQuery = "insert into payment "
				+ "(paymentChange, paymentType_id) "
				+ "values (?,?)";
		
		PreparedStatement statement = null;
		ResultSet result = null;		
		int insertedId = 0;
		
		try {
			statement = this.connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			if(payment.getChange()==null){
				statement.setInt(1, 0);
			} else {
				statement.setString(1, payment.getChange());
			}
			statement.setLong(2, payment.getPaymentId(payment.getPaymentType()));
			
			//result id of insertion
			int affectedRows = statement.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating payment failed, no rows affected.");
	        } else {
	        	//Recover inserted ID from database
	        	result = statement.getGeneratedKeys();
	        	result.next();
	            insertedId = result.getInt(1);
	        }			
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new RuntimeException("Error processing SQL - savePayment in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
		
		return insertedId;
	}
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private void saveProductsToOrder(Order order) throws SQLException {		
		String sqlQuery = "insert into order_has_product "
				+ "(order_idOrder, product_idProduct, extra_idExtra, quantity) "
				+ "values (?,?,?,?)";
		
		PreparedStatement statement = null;
		
		try {
			// prepared statement for insertion
			statement = this.connection.prepareStatement(sqlQuery);
		
			for (HashMap.Entry<Product, Integer> entry : order.getItems().entrySet()) {
				Product product = entry.getKey();
				int quantity = entry.getValue();
				
				statement.setLong(1, order.getId());
				statement.setLong(2, product.getId());
				
				if(product.getExtra()==null || product.getExtra().isEmpty()){
					statement.setNull(3, Types.INTEGER);
				} else {
					statement.setLong(3, product.getExtraId(product.getExtra()));
				}
				statement.setLong(4, quantity);
				
				statement.execute();
			}		
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - saveProductsToOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
		}
	}	
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private void saveAdditionalsToOrder(Order order) throws SQLException {		
		String sqlQuery = "insert into order_has_additional "
				+ "(order_idOrder, additional_idAdditional) "
				+ "values (?,?)";
		
		PreparedStatement statement = null;
		
		try {
			statement = this.connection.prepareStatement(sqlQuery);
			
			for(Additional additional : order.getAdditionals()){
				statement.setInt(1, order.getId());
				statement.setInt(2, additional.getId());
				
				statement.execute();
			}
		
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - saveAdditionalsToOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
		}
	}
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private HashMap<Product,Integer> setExtraFromProductsInOrder(HashMap<Product,Integer> mapProductList, 
			Order order) throws SQLException{		
		PreparedStatement statement = null;
		ResultSet result = null;
		
		for(Product product : mapProductList.keySet()){
			String sqlQuery = "select extra_idExtra from order_has_product "
					+ "where order_idOrder=? and product_idProduct=?";			
			try { 
				statement = this.connection.prepareStatement(sqlQuery);  
				
				statement.setInt(1, order.getId());
				statement.setInt(2, product.getId());
				
				result = statement.executeQuery();
				
				//If SQL query returned result
				if (result.next()) {
					product.setExtra(result.getInt("extra_idExtra"));
				}	
			}catch (SQLException exception) {
				throw new RuntimeException("Error processing SQL - setExtraFromProductsInOrder in OrderDAO: "
						+exception.getMessage());
			}  finally {
				statement.close();
				result.close();
			}
		}

		return mapProductList; 
	}
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private List<Additional> assignAdditionalsToOrder(Order order) throws SQLException{		
		List<Additional> listAdditional = new ArrayList<Additional>();
		
		String sqlQuery = "select o_a.additional_idadditional, a.name from order_has_additional o_a, additional a "
				+ "where order_idOrder=? and a.idadditional=o_a.additional_idadditional";
		
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try { 
			statement = this.connection.prepareStatement(sqlQuery);   
			statement.setInt(1, order.getId());
			result = statement.executeQuery();
			while (result.next()) {       
				Additional additional = new Additional();
				additional.setId(result.getInt("additional_idadditional"));
				additional.setName(result.getString("name"));
				listAdditional.add(additional);
			}
		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - assignAditionalsToOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}		
		return listAdditional; 
	}
	
	public void insert(Order order) {		
		this.sqlQuery = "insert into `order` "
				+ "(client_user_iduser, deliveryTime, totalPrice, idDeliveryAddress, "
				+ "status_idstatus, payment_idPayment) "
				+ "values (?,?,?,?,?,?)";
		
		setupConnectionObjects();		
		
		try {
			this.statement = this.connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			this.statement.setLong(1, order.getClient().getId());
			
			Timestamp sqlDate = castCalendarToDateTimeSQL(order.getReceiving().getDateTime());
			int addressId = getDeliveryAddressId(order.getReceiving().getAddress());
			
			this.statement.setTimestamp(2, sqlDate);
			this.statement.setDouble(3, order.getTotalPrice());
			this.statement.setInt(4, addressId);
			this.statement.setInt(5, Order.NEW_ORDER_STATUS);
			this.statement.setInt(6, savePayment(order.getPayment()));


			int affectedRows = this.statement.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating order failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = this.statement.getGeneratedKeys()) {
				this.statement.close();
	            if (generatedKeys.next()) {
	                order.setId(generatedKeys.getInt(1));
	    			saveProductsToOrder(order);
	    			saveAdditionalsToOrder(order);
	            }
	            else {
	                throw new SQLException("Creating Order failed, no ID obtained.");
	            }
	        }			
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - insert in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}	
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private HashMap<Product, Integer> getProductsFromOrder(Order order) throws SQLException{		
		ProductDAO productDao = new ProductDAO();
		HashMap<Product,Integer> mapProductQuantity = new HashMap<Product,Integer>();
		
		String sqlQuery = "select product_idProduct, quantity from order_has_product "
				+ "where order_idOrder=? ";

		PreparedStatement statement = null;
		ResultSet result = null;
		
		try { 
			statement = this.connection.prepareStatement(sqlQuery);   
			statement.setInt(1, order.getId());
			
			result = statement.executeQuery();
			
			while (result.next()) {       
				Product product = productDao.getProductById(result.getInt("product_idProduct"));
				mapProductQuantity.put(product, result.getInt("quantity"));
			}
			mapProductQuantity = setExtraFromProductsInOrder(mapProductQuantity, order);
		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getProductsFromOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
		return mapProductQuantity; 
	}
	
	public List<Order> getList() throws SQLException {		
		this.sqlQuery = "select * from `order`";
		List<Order> orderList = new ArrayList<Order>(); 

		setupConnectionObjects();
		
		try { 
			this.statement = this.connection.prepareStatement(this.sqlQuery);  
			this.result = this.statement.executeQuery(this.sqlQuery);       
			UserDAO userDao = new UserDAO();

			while (this.result.next()) { 
				Order order = new Order();
				
				order.setId(Integer.parseInt(this.result.getString("idOrder")));
				order.setTotalPrice(this.result.getDouble("totalPrice"));
				order.setReceiving(getReceivingFromOrder((this.result.getInt("idDeliveryAddress"))));
				order.setPayment(getPaymentFromOrder(this.result.getInt("payment_idPayment")));
				
				//SET ORDER STATUS
				order.setStatus(this.result.getInt("status_idstatus"));				
				//SET TIME OF RECEIVING
				Calendar receivingTime = castStringToCalendar((this.result.getString("deliveryTime")));
				order.getReceiving().setDateTime(receivingTime);				
				//ASSIGN PRODUCTS TO ORDER
				order.setItems(this.getProductsFromOrder(order));			
				//ASSIGN ADDITIONALS TO ORDER
				order.setAdditionals(this.assignAdditionalsToOrder(order));			
				//ASSIGN CLIENT
				order.setClient((Client) userDao.getUserById(this.result.getInt("client_user_iduser")));
				
				orderList.add(order);
			} 

		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - assignAdditionalsToOrder in OrderDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
		}		
		return orderList;
   }
	
	public List<Additional> getAdditionalsList() {		
		this.sqlQuery = "select * from `additional`";		
		List<Additional> listAdditional = new ArrayList<Additional>(); 

		setupConnectionObjects();
		
		try { 
			this.statement = this.connection.prepareStatement(this.sqlQuery);	    	   
			this.result = this.statement.executeQuery(this.sqlQuery);     

			while (this.result.next()) { 
				Additional additional = new Additional();
				
				additional.setId(Integer.parseInt(this.result.getString("idadditional")));
				additional.setName(this.result.getString("name"));
				
				listAdditional.add(additional);			
			}
		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAdditionalsList in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}		
		return listAdditional; 
   }
	
	public Additional getAdditionalById(int idAdditional) {		
        Additional additional = new Additional();
        this.sqlQuery = "select * from additional where idadditional=?";

        setupConnectionObjects();
        
        try {
            this.statement = this.connection.prepareStatement(this.sqlQuery);
            this.statement.setInt(1, idAdditional);
            this.result = this.statement.executeQuery();

            if (this.result.next()) {				
            	additional.setId(Integer.parseInt(this.result.getString("idadditional")));
				additional.setName(this.result.getString("name"));
            }
        } catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAdditionalsList in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}		
		return additional; 
    }
	
	//Must open new Statement, Query and Result objects to not interfere on calling methods
	private Payment getPaymentFromOrder(int idPayment) throws SQLException {		
		String paymentType = null;
		String change = null;
        String sqlQuery = "select * from payment where idpayment=?";
        Payment payment = null;

        PreparedStatement statement = null;
		ResultSet result = null;
        
        try {
            statement = this.connection.prepareStatement(sqlQuery);
            statement.setInt(1, idPayment);
            result = statement.executeQuery();

            if (result.next()) {
				change = result.getString("paymentChange");
				paymentType = result.getString("paymentType_id");
				
				payment = new Payment(idPayment, paymentType, change);
            }
        } catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getPaymentFromOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}		
		return payment; 
	}

	public Order getOrderById(int orderId) throws ParseException {		
        Order order = new Order();
		UserDAO userDao = new UserDAO();
        this.sqlQuery = "select * from order where idProduct=?";

        setupConnectionObjects();
        
        try {
            this.statement = this.connection.prepareStatement(this.sqlQuery);
            this.statement.setInt(1, orderId);
            this.result = this.statement.executeQuery();

            if (this.result.next()) {
				
				order.setId(Integer.parseInt(this.result.getString("idOrder")));
				order.setTotalPrice(this.result.getDouble("totalPrice"));
				order.setReceiving(getReceivingFromOrder((this.result.getInt("idDeliveryAddress"))));
				
				//SET TIME OF RECEIVING
				Calendar time = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
				time.setTime(dateFormat.parse(this.result.getString("deliveryTime")));
				order.getReceiving().setDateTime(time);
				
				//ASSIGN PRODUCTS TO ORDER
				order.setItems(this.getProductsFromOrder(order));
			
				//ASSIGN ADDITIONALS TO ORDER
				order.setAdditionals(this.assignAdditionalsToOrder(order));
				
				//ASSIGN CLIENT
				order.setClient((Client) userDao.getUserById(this.result.getInt("client_user_iduser")));

            }
        } catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getOrderById in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
        
        return order;
    }
			
	public void delete(Order order) {		
		this.sqlQuery = "delete from order where idOrder=?";
		
		setupConnectionObjects();
		
		try {
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			this.statement.setInt(1, order.getId());
			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - delete in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}
	
	public List<String> getPaymentTypes(){		
		this.sqlQuery = "select payment from payment_type";
		List<String> paymentTypes = new ArrayList<String>(); 

		setupConnectionObjects();
		
		try { 
			this.statement = this.connection.prepareStatement(this.sqlQuery);	    	   
			this.result = this.statement.executeQuery(this.sqlQuery);       

			while (this.result.next()) { 			
				paymentTypes.add(this.result.getString("payment"));
			}

		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getPaymentTypes in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		return paymentTypes; 
	}
}
