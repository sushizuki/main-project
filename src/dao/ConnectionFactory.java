/** 
 * ConnectionFactory.java to define ConnectionFactory 
 * Factory of connections for databases.
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Design Pattern Factory
public class ConnectionFactory implements dao.Connection{
	
	private final String host = "jdbc:mysql://127.0.0.1/sushizuki";
	private final String user = "root";
	private final String password = "";
	
	public Connection getConnection() {		
		Connection myConnection = null;		
		
		try {
            Class.forName("com.mysql.jdbc.Driver");  
			myConnection = MySqlConnection(host, user, password);
		} catch (SQLException | ClassNotFoundException exception) {
			throw new RuntimeException("Error stablishing connection with MySQL Database"
					+ " - getConnection in ConnectionFactory: "
					+exception.getMessage());
		}
		
		return myConnection;
	}
	
	private Connection MySqlConnection(final String host, final String user, 
			final String password) throws SQLException{
		
		return DriverManager.getConnection(host, user, password);
	}
}
