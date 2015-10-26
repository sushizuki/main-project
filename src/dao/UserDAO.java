package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.User;
import dao.ConnectionFactory;

public class UserDAO {



	private Connection con;

	public UserDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void insert(User user) {
		String sql = "insert into product " +
		"(name,email,address,password,phone)" +
		" values (?,?,?,?,?)";
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
		// set values for each '?'
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getAddress());
		stmt.setString(4, user.getPassword());
		stmt.setString(5, user.getPhone());
		
		// execute
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	public void update(User user) {
		String sql = "update user set name=?, email=?, address=?," +
		"phone=? password=? where iduser=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getAddress());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getPassword());
			stmt.setInt(6, user.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int userID) {
		String sql = "delete from user where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	    public List<User> getAllUsers() {
	        List<User> users = new ArrayList<User>();
	        try {
	            Statement statement = con.createStatement();
	            ResultSet rs = statement.executeQuery("select * from users");
	            while (rs.next()) {
	                User user = new User();
	                user.setId(rs.getInt("userid"));
	                user.setEmail(rs.getString("email"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return users;
	    }

	    public User getUserById(int userId) {
	        User user = new User();
	        try {
	            PreparedStatement preparedStatement = con.
	                    prepareStatement("select * from users where userid=?");
	            preparedStatement.setInt(1, userId);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                user.setId(rs.getInt("userid"));
	                user.setEmail(rs.getString("email"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return user;
	    }
	}
