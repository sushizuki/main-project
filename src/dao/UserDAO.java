/** 
 * UserDAO.java to define UserDAO 
 * This class persists into or from database any information about users 
 */ 

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Address;
import domain.Administrator;
import domain.Client;
import domain.User;
import exceptions.UserExceptions;

//Design pattern DAO
public class UserDAO extends DataAccessObject{

	public UserDAO() {
		super();
	}

	//Must open new Statement and Result objects to not interfere on calling methods
	private Client getClientById(int clientId) throws SQLException{
		
		assert clientId > 0: "Invalid Client ID";
		
		String sqlQuery = "select address_idAddress from client where user_iduser=?";
		Client client = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = this.connection.prepareStatement(sqlQuery);
			statement.setInt(1, clientId);
			
			result = statement.executeQuery();

			//If SQL query returned result
			if (result.next()) {
				AddressDAO daoAddress = new AddressDAO();
				Address address = daoAddress.getAddressById(result.getInt("address_idAddress"));
				result.getString("address_idAddress");
				client = new Client(address);
			} else {
				//nothing to do, no client found
			}
		}  catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getClientById in UserDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
		return client;
	}

	//Must open new Statement and Result objects to not interfere on calling methods
	private Administrator getAdministratorById(int id) throws SQLException{	
		
		assert id > 0: "Invalid Administrator ID";
		
		String sqlQuery = "select 1 from administrator where user_iduser=?";
		Administrator administrator = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = this.connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			result = statement.executeQuery();

			if (result.next()) {
				administrator = new Administrator();
			} else {
				//nothing to do
			}

		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAdministratorById in UserDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
		return administrator;
	}
	
	//Must open new Statement and Result objects to not interfere on calling methods
	private boolean isUserClient(int idUser) throws SQLException{
		
		assert idUser > 0: "Invalid User id";
		
		String sqlQuery = "select 1 from client where user_iduser=?";
		boolean userIsClient = true;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = this.connection.prepareStatement(sqlQuery);
			statement.setInt(1, idUser);
			result = statement.executeQuery();

			if (result.next()) {
				userIsClient = true;
			} else {
				userIsClient = false;
			}

		}  catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - isUserClient in UserDAO: "
					+exception.getMessage());
		}  finally {
			statement.close();
			result.close();
		}
		
		return userIsClient;
	}
	
	/**
	 * Inserts into database a new Client
	 * @param Client user object containing full client details
	 */
	public void insert(Client user) {
		
		assert user != null: "Invalid Client: null value cannot be accepted";

		String sqlQuery = "insert into user "
				+ "(name,email,phone,password,address) "
				+ "values (?,?,?,?,?)";

		setupConnectionObjects();

		try {
			// prepared statement for insertion
			this.statement = this.connection.prepareStatement(sqlQuery);

			// set values for each '?'
			this.statement.setString(1, user.getName());
			this.statement.setString(2, user.getEmail());
			this.statement.setString(3, user.getPhone());
			this.statement.setString(4, user.getPassword());
			AddressDAO daoAddress = new AddressDAO();
			this.statement.setInt(5, daoAddress.insert(user.getAddress()) );

			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - insert in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}
	
	/**
	 * Updates into database a User details
	 * @param User object containing full details
	 */
	public void update(User user) {
		
		assert user != null: "Invalid User: null value cannot be accepted";

		String sqlQuery = "update user set name=?, email=?, phone=?," +
				" password=? where iduser=?";

		setupConnectionObjects();

		try {
			this.statement = this.connection.prepareStatement(sqlQuery);
			this.statement.setString(1, user.getName());
			this.statement.setString(2, user.getEmail());
			this.statement.setString(3, user.getPhone());
			this.statement.setString(4, user.getPassword());
			this.statement.setInt(5, user.getId());
			this.statement.execute();
			AddressDAO daoAddress = new AddressDAO();
			daoAddress.update( ((Client) user).getAddress() );
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - update in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}
	
	/**
	 * Performs login of a user according to email and password in database
	 * @param email, password strings of user's email and user's password from login form
	 */
	public User login(String email, String password){

		assert email != null: "Invalid User email: null value cannot be accepted";
		assert password != null: "Invalid User password: null value cannot be accepted";
		
		String sqlQuery = "select * from user where email=? and password=?";
		User user = null;

		setupConnectionObjects();

		try {
			this.statement = this.connection.prepareStatement(sqlQuery);
			this.statement.setString(1, email);
			this.statement.setString(2, password);

			this.result = this.statement.executeQuery();

			if (this.result.next()) {
				final int userId = this.result.getInt("iduser");
				
				if(isUserClient(userId)){  
					user = getClientById(userId);
				} else {
					user = getAdministratorById(userId);
				}
				user.setId(userId);
				user.setName(this.result.getString("name"));
				user.setEmail(this.result.getString("email"));
				user.setPhone(this.result.getString("phone"));
				user.setPassword(this.result.getString("password"));	        
			} else {
				//nothing to do, no user found
			}

		} catch (SQLException | UserExceptions exception) {
			throw new RuntimeException("Error processing SQL - login in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		return user;
	}
	
	/**
	 * Returns a User from database based on ID
	 * @param int user id number
	 * @throws UserExceptions 
	 */
	public User getUserById(int userId) throws UserExceptions {	
		
		assert userId > 0: "Invalid User ID";
		
		String sqlQuery = "select * from user where iduser=?";
		User user = null;
		
		setupConnectionObjects();

		try {
			this.statement = this.connection.prepareStatement(sqlQuery);
			this.statement.setInt(1, userId);
			this.result = this.statement.executeQuery();

			if (this.result.next()) {
				if(isUserClient(userId)){  
					user = getClientById(userId);
				} else {
					user = getAdministratorById(userId);
				}
				user.setId(Integer.parseInt(this.result.getString("iduser")));
				user.setName(this.result.getString("name"));
				user.setEmail(this.result.getString("email"));
				user.setPhone(this.result.getString("phone"));
				user.setPassword(this.result.getString("password"));        	  
			} else {
				//do nothing
			}

		}  catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getUserById in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		return user;
	}
}
