package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Design Pattern Factory
public class ConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/sushizuki", "root", "admin");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
