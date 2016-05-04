/** 
 *    UserDAO.java to define UserDAO 
 *    {purpose} 
 */ 

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import domain.Address;
import domain.Administrator;
import domain.Client;
import domain.User;

//Design pattern DAO
public class UserDAO extends DataAccessObject{

	public UserDAO() {
		super();
	}

	public void insert(Client user) {

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
			this.statement.setInt(5, saveAddress(user.getAddress()) );

			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - insert in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}

	private int saveAddress(Address address) {

		String sqlQuery = "insert into address "
				+ "(cep, address, addressComplement) "
				+ "values (?,?,?)";

		setupConnectionObjects();

		try {
			// prepared statement for insertion
			this.statement = this.connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

			// set values for each '?'
			this.statement.setString(1, address.getCep());
			this.statement.setString(2, address.getAddress());
			this.statement.setString(3, address.getComplement());

			int affectedRows = this.statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating address failed, no rows affected.");
			}

			try (ResultSet generatedKeys = this.statement.getGeneratedKeys()) {
				this.statement.close();
				if (generatedKeys.next()) {
					return (int) generatedKeys.getLong(1);
				}
				else {
					throw new SQLException("Creating address failed, no ID obtained.");
				}
			}
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - saveAddress in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}

	private void updateAddress(Address address) {

		String sqlQuery = "update address set cep=?, address=?, addressComplement=? "
				+ "where idAddress=?";

		setupConnectionObjects();

		try {
			this.statement = this.connection.prepareStatement(sqlQuery);
			this.statement.setString(1, address.getCep());
			this.statement.setString(2, address.getAddress());
			this.statement.setString(3, address.getComplement());
			this.statement.setInt(4, address.getId());
			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - updateAddress in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}

	public User login(String email, String password){

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

		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - login in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		return user;
	}

	//Must open new Statement and Result objects to not interfere on calling methods
	private Client getClientById(int clientId) throws SQLException{
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
		String sqlQuery = "select 1 from administrator where user_iduser=?";
		Administrator administrator = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		setupConnectionObjects();

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
	
	public User getUserById(int userId) {	
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
	
	//Must open new Statement and Result objects to not interfere on calling methods
	private boolean isUserClient(int idUser) throws SQLException{
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
	
	public void update(User user) {

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
			updateAddress( ((Client)user).getAddress() );
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - update in UserDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}
}
