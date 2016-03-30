package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Design Pattern Factory
//MODIFY NAME TO REFER TO A MYSQL CONNECTION
public class ConnectionFactory implements dao.Connection{
	public Connection getConnection() {
		try {
            Class.forName("com.mysql.jdbc.Driver");  
			return DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/sushizuki", "root", "");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e+": Erro na comunicação com banco de dados");
		}
	}
}
