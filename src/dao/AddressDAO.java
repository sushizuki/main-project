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
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into address " +
		"(cep,address,addressComplement)" +
		" values (?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
		// prepared statement for insertion
		stmt = con.prepareStatement(sql);
		
		// set values for each '?'
		stmt.setString(1, address.getCep());
		stmt.setString(2, address.getAddress());
		stmt.setString(3, address.getComplement());
		
		// execute
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
	
	public Address getAddressById(int addressId) {
		this.con = new ConnectionFactory().getConnection();
		
        Address address = null;
        String cep, addr, complement;
        int id;
        String sql = "select * from address where idAddress=?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, addressId);
            rs = stmt.executeQuery();

            if (rs.next()) {
				id = Integer.parseInt(rs.getString("idAddress"));
				cep = rs.getString("cep");
				addr = rs.getString("address");
				complement = rs.getString("addressComplement");
				
				address = new Address(id, cep, addr, complement);

            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING PRODUCT: "+e.getMessage());
        }  finally {
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
        
        return address;
    }
	
	public void update(Address address) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "update address set cep=?, address=?, addressComplement=? where idAddress=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, address.getCep());
			stmt.setString(2, address.getAddress());
			stmt.setString(3, address.getComplement());
			stmt.setInt(4, address.getId());
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
	
	public void delete(Address address) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "delete from address where idAddress=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, address.getId());
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
}
