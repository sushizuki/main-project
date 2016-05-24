/**
 * AddressDAO.java to define AddressDAO
 * This class persists into or from database any information about addresses
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import domain.Address;
import exceptions.EmptyFieldException;

//Design pattern DAO
public class AddressDAO extends DataAccessObject{

	private String sqlQuery;	

	public AddressDAO() {
		super();
	}

	/** 
	 * Registers an address into the database and returns its generated id
	 * @param Address object containing full address details
	 * @return int id number generated from database
	 */
	public int insert(Address address) {
	
		assert address != null: "Invalid address: null value cannot be accepted";
		
		this.sqlQuery = "insert into address " +
						"(cep,address,addressComplement)" +
						" values (?,?,?)";

		setupConnectionObjects();
		
		int insertedId = 0;

		try {
			// prepare statement for insertion returning generated keys from database
			this.statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

			// Replace '?' characters from sql variable into statement
			this.statement.setString(1, address.getCep());
			this.statement.setString(2, address.getAddress());
			this.statement.setString(3, address.getComplement());

			//result id of insertion
			int affectedRows = this.statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating address failed, no rows affected.");
			} else {
				ResultSet generatedKeys = this.statement.getGeneratedKeys();
				insertedId = (int) generatedKeys.getLong(1);
			}
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - insert in AddressDAO: "
										+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		
		return insertedId;
	}

	/** 
	 * getAddress searchs for an address into the database
	 * @param address string description of the address that will be searched
	 * @return Address object containing full address details
	 * @throws EmptyFieldException 
	 */
	public Address getAddress(String lookingAddress) throws EmptyFieldException {
		
		assert lookingAddress != null: "Invalid address: null value cannot be accepted";
		
		Address address = null;
		String cep = null;
		String complement = null;
		int id = 0;
		
		this.sqlQuery = "select * from address where address=? limit 1";

		setupConnectionObjects();

		try {
			this.statement = connection.prepareStatement(this.sqlQuery);

			//Replace '?' characters from sql variable into statement
			this.statement.setString(1, lookingAddress);

			this.result = statement.executeQuery();

			//If SQL query returned result
			if (this.result.next()) {
				id = Integer.parseInt(result.getString("idAddress"));
				cep = result.getString("cep");
				complement = result.getString("addressComplement");

				address = new Address(id, cep, lookingAddress, complement);
			} else {
				//No address found, nothing to do
			}
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAddress in AddressDAO: "
										+exception.getMessage());
		} finally {
			closeConnectionObjects();
		}

		return address;
	}

	/**
	 * getAddressById given an int id, searches for and addres into database
	 * @param addressId int id number to be searched
	 * @return Address object containing full address details
	 * @throws EmptyFieldException 
	 */
	public Address getAddressById(int addressId) throws EmptyFieldException {

		Address address = null;
		String cep = null;
		String complement = null;
		String addressDescription = null;
		int id = 0;
		
		this.sqlQuery = "select * from address where idAddress=?";		

		setupConnectionObjects();
		
		try {
			this.statement = connection.prepareStatement(this.sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			this.statement.setInt(1, addressId);
			
			this.result = this.statement.executeQuery();

			//If SQL query returned result
			if (this.result.next()) {
				id = Integer.parseInt(this.result.getString("idAddress"));
				cep = this.result.getString("cep");
				addressDescription = this.result.getString("address");
				complement = this.result.getString("addressComplement");

				address = new Address(id, cep, addressDescription, complement);

			}else {
				//No address found, nothing to do
			}
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getAddressById in AddressDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
		
		return address;
	}

	/**
	 * update address in the database
	 * @param address object containing full address details
	 */
	public void update(Address address) {

		assert address != null: "Invalid address: null value cannot be accepted";
		
		this.sqlQuery = "update address set cep=?, address=?, "
				+ "addressComplement=? where idAddress=?";

		setupConnectionObjects();
		
		try {
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			this.statement.setString(1, address.getCep());
			this.statement.setString(2, address.getAddress());
			this.statement.setString(3, address.getComplement());
			this.statement.setInt(4, address.getId());
			
			this.statement.execute();
			
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - update in AddressDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}

	/**
	 * delete address from database
	 * @param address object containing full address details
	 */
	public void delete(Address address) {		
		
		assert address != null: "Invalid address: null value cannot be accepted";
		
		this.sqlQuery = "delete from address where idAddress=?";

		setupConnectionObjects();
		
		try {
			this.statement = this.connection.prepareStatement(sqlQuery);
			this.statement.setInt(1, address.getId());
			this.statement.execute();
			this.statement.close();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - delete in AddressDAO: "
					+exception.getMessage());
		}  finally {
			closeConnectionObjects();
		}
	}
}
