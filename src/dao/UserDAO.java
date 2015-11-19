package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Address;
import domain.Administrator;
import domain.Client;
import domain.User;

//Design pattern DAO
public class UserDAO {
	
	private Connection con;

	public UserDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
		
	public User getUserById(int userId) {
		
		String sql = "select * from user where iduser=?";
		
		try {
          PreparedStatement preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, userId);
          ResultSet rs = preparedStatement.executeQuery();

    	  User usr = null;
          
          if (rs.next()) {
        	  if(isUserClient(userId)){  
        		  usr = getClientById(userId);
        	  } else {
        		  usr = getAdmById(userId);
        	  }
        	  usr.setId(Integer.parseInt(rs.getString("iduser")));
        	  usr.setName(rs.getString("name"));
        	  usr.setEmail(rs.getString("email"));
        	  usr.setPhone(rs.getString("phone"));
        	  usr.setPassword(rs.getString("password"));
        	  
        	  
        	  
        	  return usr;
        	  
          } 
          
      } catch (SQLException e) {
			throw new RuntimeException("ERROR SEARCHING FOR USER INTO DATABASE: "+e.getMessage());
      }
		return null;
    }
	
	public boolean isUserClient(int idUser){
		
		String sql = "select * from client where user_iduser=?";
		
		try {
          PreparedStatement preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, idUser);
          ResultSet rs = preparedStatement.executeQuery();

          if (rs.next()) {
        	  return true; 
          } else {
        	  return false;
          }
          
      } catch (SQLException e) {
			throw new RuntimeException("ERROR SEARCHING FOR CLIENT INTO DATABASE: "+e.getMessage());
      }
	}
	
	private Client getClientById(int id){
		
		String sql = "select * from client where user_iduser=?";
		
		try {
          PreparedStatement preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, id);
          ResultSet rs = preparedStatement.executeQuery();

          if (rs.next()) {
        	  AddressDAO daoAddress = new AddressDAO();
        	  Address addr = daoAddress.getAddressById(rs.getInt("address_idAddress"));
        	  rs.getString("address_idAddress");
        	  return new Client(addr);
          } else {
        	  return null;
          }
          
      } catch (SQLException e) {
			throw new RuntimeException("ERROR SEARCHING FOR CLIENT INTO DATABASE: "+e.getMessage());
      }
	}
	
	private Administrator getAdmById(int id){
		
		String sql = "select * from administrator where user_iduser=?";
		
		try {
          PreparedStatement preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, id);
          ResultSet rs = preparedStatement.executeQuery();

          if (rs.next()) {
        	  return new Administrator();
          } else {
        	  return null;
          }
          
      } catch (SQLException e) {
			throw new RuntimeException("ERROR SEARCHING FOR ADMINISTRATOR INTO DATABASE: "+e.getMessage());
      }
	}
}
