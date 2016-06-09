/**
* OrderDAO.java to define OrderDAO
* This class persists into or from database any information about orders
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
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

//Design pattern DAO
public class OrderDAO extends DataAccessObject{

	private String sqlQuery;

	public OrderDAO() {
		super();
	}

	/*
	 * Converts Calendar date to Timestamp object type
	 */
	private Timestamp castCalendarToDateTimeSQL(Calendar calendar){

		assert calendar != null: "Invalid Calendar: null value cannot be accepted";

		Timestamp sqlDate = new Timestamp(calendar.getTime().getTime());
		return sqlDate;
	}

	/*
	 * Converts string date to Calendar object type
	 */
	private Calendar castStringToCalendar(String date){

		assert date != null: "Invalid Date: null value cannot be accepted";

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

	/*
	 * Retuns the registered Address ID from database, if not in database inserts it
	 */
	private int getDeliveryAddressId(Address deliveryAddress) throws EmptyFieldException, InvalidFormatException {

		assert deliveryAddress != null: "Invalid Address: null value cannot be accepted";

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

	/*
	 * Returns the Order's receiving method (Collect, Delivery) according to address
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private Receiving getReceivingFromOrder(int addressId) throws SQLException, EmptyFieldException, InvalidFormatException{

		assert addressId > 0: "Invalid Address ID";

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
        		String  cep = result.getString("cep");
				String addressDescription = result.getString("address");
				String complement = result.getString("addressComplement");

				Address address = new Address(addressId, cep, addressDescription, complement);
				Collect collect = new Collect();
				if(address.getAddress().equals(collect.getAddress())){
					receivingMethod = new Collect();
				} else {
					receivingMethod = new Delivery(address);
				}
            } else {
            	//No address found, nothing to do
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

	/*
	 * Saves into database payment details and returns ID of saved payment of an order
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private int savePayment(Payment payment) throws SQLException {

		assert payment != null: "Invalid Payment: null value cannot be accepted";

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

	/*
	 * Returns the saved Payment of an order based on ID
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private Payment getPaymentFromOrder(int idPayment) throws SQLException {

		assert idPayment > 0: "Invalid Payment ID";

        String sqlQuery = "select * from payment where idpayment=?";
        Payment payment = null;

        PreparedStatement statement = null;
		ResultSet result = null;

        try {
            statement = this.connection.prepareStatement(sqlQuery);
            statement.setInt(1, idPayment);
            result = statement.executeQuery();

            if (result.next()) {
            	String change = result.getString("paymentChange");
            	String paymentType = result.getString("paymentType_id");

				payment = new Payment(idPayment, paymentType, change);
            }
        } catch (SQLException | InvalidFormatException exception) {
			throw new RuntimeException("Error processing SQL - getPaymentFromOrder in OrderDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
		return payment;
	}

	/*
	 * Saves in database selected products of the order
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private void saveProductsToOrder(Order order) throws SQLException {

		assert order != null: "Invalid Order: null value cannot be accepted";

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

	/*
	 * Saves in database selected additionals of the order
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private void saveAdditionalsToOrder(Order order) throws SQLException {

		assert order != null: "Invalid Order: null value cannot be accepted";

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

	/*
	 * Saves in database selected extras for every product of the order
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
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

	/*
	 * Process to order the selected additionals
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private List<Additional> assignAdditionalsToOrder(Order order) throws SQLException, InvalidFormatException, EmptyFieldException{

		assert order != null: "Invalid Order: null value cannot be accepted";

		List<Additional> listAdditional = new ArrayList<Additional>();

		String sqlQuery = "select o_a.additional_idadditional, a.name from order_has_additional o_a, additional a "
				+ "where order_idOrder=? and a.idadditional=o_a.additional_idadditional";

		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = this.connection.prepareStatement(sqlQuery);
			statement.setInt(1, order.getId());
			result = statement.executeQuery();
			while (result.next())
				try {
					{
						

						Additional additional = new Additional();
						additional.setId(result.getInt("additional_idadditional"));
						try {
							additional.setName(result.getString("name"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						listAdditional.add(additional);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	/*
	 * Returns the list of products of an order and their quantities
	 * Must open new Statement, Query and Result objects to not interfere on calling methods
	 */
	private HashMap<Product, Integer> getProductsFromOrder(Order order) throws SQLException{

		assert order != null: "Invalid Order: null value cannot be accepted";

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

	/**
	 * Saves order in database
	 * @param order containing full order details
	 * @throws EmptyFieldException 
	 * @throws InvalidFormatException 
	 */
	public void insert(Order order) throws EmptyFieldException, InvalidFormatException {

		assert order != null: "Invalid Order: null value cannot be accepted";

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

	        //Save others details of the order
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

	/**
	 * Returns all saved orders from database
	 * @throws EmptyFieldException 
	 * @throws InvalidFormatException 
	 */
	public List<Order> getList() throws EmptyFieldException, InvalidFormatException {
		this.sqlQuery = "select * from `order` order by deliveryTime desc";
		List<Order> orderList = new ArrayList<Order>();

		setupConnectionObjects();

		try {
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			this.result = this.statement.executeQuery(this.sqlQuery);
			UserDAO userDao = new UserDAO();

			while(this.result.next()) {
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

	/**
	 * Returns all options of additionals to an order from database
	 * @return List<Additional> List of saved Additionals from database
	 * @throws InvalidFormatException 
	 * @throws EmptyFieldException 
	 */
	public List<Additional> getAdditionalsList() throws InvalidFormatException, EmptyFieldException {
		this.sqlQuery = "select * from `additional`";
		List<Additional> listAdditional = new ArrayList<Additional>();

		setupConnectionObjects();

		try {
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			this.result = this.statement.executeQuery(this.sqlQuery);

			while (this.result.next()) {
				if(!this.result.getString("idadditional").isEmpty() 
						&& this.result.getString("idadditional") !=null){
					Additional additional = new Additional();
					
					additional.setId(Integer.parseInt(this.result.getString("idadditional")));
					
					try {
						additional.setName(this.result.getString("name"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					listAdditional.add(additional);
				} else {
					// Nothing to do
				}				
			}
		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAdditionalsList in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}

		return listAdditional;
	}

	/**
	 * Returns additional from database according to ID
	 * @param int id number generated from database
	 * @return Additional object from database
	 * @throws InvalidFormatException 
	 * @throws EmptyFieldException 
	 */
	public Additional getAdditionalById(int idAdditional) throws InvalidFormatException, EmptyFieldException, Exception {

		assert idAdditional > 0: "Invalid Additional ID";

        Additional additional = new Additional();
        this.sqlQuery = "select * from additional where idadditional=?";

        setupConnectionObjects();

        try {
            this.statement = this.connection.prepareStatement(this.sqlQuery);
            this.statement.setInt(1, idAdditional);
            this.result = this.statement.executeQuery();

            if (this.result.next()) {
            	additional.setId(Integer.parseInt(this.result.getString("idadditional")));
				try {
					additional.setName(this.result.getString("name"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }else{
            	//Do nothing
            }
        } catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAdditionalsList in OrderDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		return additional;
    }

	/**
	 * Returns order from database according to ID
	 * @param int id of the order to be searched within database
	 * @return Order object containing full order details from database
	 * @throws EmptyFieldException 
	 * @throws InvalidFormatException 
	 */
	public Order getOrderById(int orderId) throws ParseException, EmptyFieldException, InvalidFormatException {

		assert orderId > 0: "Invalid Order ID";

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

	/**
	 * Delete order from database according to ID
	 * @param Order object containing id which is to delete
	 */
	public void delete(Order order) {

		assert order != null: "Invalid Order: null value cannot be accepted";

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

	/**
	 * Returns all options of Payment of an order from database
	 * @return List<String> List of saved Payment types from database
	 */
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
