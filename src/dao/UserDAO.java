package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import domain.User;
import model.ConnectionFactory;

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
	
}
