/** 
*    UserDAO.java to define UserDAO 
*    {purpose} 
*/ 

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import domain.Address;
import domain.Administrator;
import domain.Client;
import domain.User;

//Design pattern DAO
public class UserDAO {
	
	private Connection con;

	public UserDAO() {

	}
	
	public void insert(Client user) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into user " +
			"(name,email,phone,password,address)" +
			" values (?,?,?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			// prepared statement for insertion
			stmt = con.prepareStatement(sql);
			
			// set values for each '?'
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setString(4, user.getPassword());
			stmt.setInt(5, saveAddress(user.getAddress()) );
			
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
	
	private int saveAddress(Address a) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into address " +
			"(cep, address, addressComplement)" +
			" values (?,?,?)";
		
		PreparedStatement stmt = null;
		
		try {
			// prepared statement for insertion
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set values for each '?'
			stmt.setString(1, a.getCep());
			stmt.setString(2, a.getAddress());
			stmt.setString(3, a.getComplement());
			
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
	
	public void update(User user) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "update user set name=?, email=?, phone=?," +
		" password=? where iduser=?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setString(4, user.getPassword());
			stmt.setInt(5, user.getId());
			stmt.execute();
			updateAddress( ((Client)user).getAddress() );
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
	
	private void updateAddress(Address a) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "update address set cep=?, address=?, addressComplement=? " +
		" where idAddress=?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, a.getCep());
			stmt.setString(2, a.getAddress());
			stmt.setString(3, a.getComplement());
			stmt.setInt(4, a.getId());
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
	
	public User login(String email, String password) throws SQLException{
		
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "select * from user where email=? and password=?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
          preparedStatement = con.prepareStatement(sql);
          preparedStatement.setString(1, email);
          preparedStatement.setString(2, password);
          rs = preparedStatement.executeQuery();

    	  User usr = null;
	          
          if (rs.next()) {
        	  if(isUserClient(rs.getInt("iduser"))){  
        		  usr = getClientById(rs.getInt("iduser"));
        	  } else {
        		  usr = getAdmById(rs.getInt("iduser"));
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
	    } finally {
	    	try {
	  	  	  	if(this.con != null) {
	  	            con.close();
	  	        }
	  	
	  	        if(preparedStatement != null) {
	  	        	preparedStatement.close();
	  	        }
	  	
	  	        if(rs != null) {
	  	            rs.close();
	  	        }
	      	  } catch(SQLException e){}
	      }
		return null;
	}
		
	public User getUserById(int userId) {
		this.con = new ConnectionFactory().getConnection();		
		String sql = "select * from user where iduser=?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
          preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, userId);
          rs = preparedStatement.executeQuery();

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
      }finally {
    	  try {
	  	  	if(this.con != null) {
	            con.close();
	        }
	
	        if(preparedStatement != null) {
	        	preparedStatement.close();
	        }
	
	        if(rs != null) {
	            rs.close();
	        }
    	  } catch(SQLException e){ }
      }
		return null;
    }
	
	public boolean isUserClient(int idUser){
		this.con = new ConnectionFactory().getConnection();
		String sql = "select 1 from client where user_iduser=?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;		
		
		try {
          preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, idUser);
          rs = preparedStatement.executeQuery();

          if (rs.next()) {
        	  return true; 
          } else {
        	  return false;
          }
          
      } catch (SQLException e) {
			throw new RuntimeException("ERROR SEARCHING FOR CLIENT INTO DATABASE: "+e.getMessage());
      } finally {
    	  try {
	  	  	  	if(con != null) {
	  	            con.close();
	  	        }
	  	
	  	        if(preparedStatement != null) {
	  	        	preparedStatement.close();
	  	        }
	  	
	  	        if(rs != null) {
	  	            rs.close();
	  	        }
	      	  } catch(SQLException e){ }
         }
	}
	
	private Client getClientById(int id){
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "select address_idAddress from client where user_iduser=?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
          preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, id);
          rs = preparedStatement.executeQuery();

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
      } finally {
    	  try {
  	  	  	if(con != null) {
  	            con.close();
  	        }
  	
  	        if(preparedStatement != null) {
  	        	preparedStatement.close();
  	        }
  	
  	        if(rs != null) {
  	            rs.close();
  	        }
	      } catch(SQLException e){}
      }
	}
	
	private Administrator getAdmById(int id){
		this.con = new ConnectionFactory().getConnection();		
		String sql = "select 1 from administrator where user_iduser=?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
          preparedStatement = con.prepareStatement(sql);
          preparedStatement.setInt(1, id);
          rs = preparedStatement.executeQuery();

          if (rs.next()) {
        	  return new Administrator();
          } else {
        	  return null;
          }
          
      } catch (SQLException e) {
			throw new RuntimeException("ERROR SEARCHING FOR ADMINISTRATOR INTO DATABASE: "+e.getMessage());
      } finally {
    	  try {
    	  	  	if(this.con != null) {
    	            con.close();
    	        }
    	
    	        if(preparedStatement != null) {
    	        	preparedStatement.close();
    	        }
    	
    	        if(rs != null) {
    	            rs.close();
    	        }
  	      } catch(SQLException e){}
       }		
	}
}
