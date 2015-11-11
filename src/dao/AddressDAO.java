package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Address;

//Design pattern DAO
public class AddressDAO {
	private Connection con;

	public AddressDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void insert(Address address) {
		String sql = "insert into address " +
		"(cep,address,addressComplement)" +
		" values (?,?,?)";
		
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
		// set values for each '?'
		stmt.setString(1, address.getCep());
		stmt.setString(2, address.getAddress());
		stmt.setString(3, address.getComplement());
		
		// execute
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	public Address getAddressById(int addressId) {
        Address address = null;
        String cep, addr, complement;
        int id;
        String sql = "select * from address where idAddress=?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, addressId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
				id = Integer.parseInt(rs.getString("idAddress"));
				cep = rs.getString("cep");
				addr = rs.getString("address");
				complement = rs.getString("addressComplement");
				
				address = new Address(id, cep, addr, complement);

            }
            
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING PRODUCT: "+e.getMessage());
        }
        
        return address;
    }
	
	public void update(Address address) {
		
		String sql = "update address set cep=?, address=?, addressComplement=? where idAddress=?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, address.getCep());
			stmt.setString(2, address.getAddress());
			stmt.setString(3, address.getComplement());
			stmt.setInt(4, address.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Address address) {
		String sql = "delete from address where idAddress=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, address.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	/*public void finalize() {
		try {
			if(!this.con.isClosed()){
				this.con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR, could not close connection: "+e.getMessage());
		}
	}*/
}
