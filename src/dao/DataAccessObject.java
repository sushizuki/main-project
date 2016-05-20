package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccessObject {
	
	protected Connection connection;
	protected PreparedStatement statement; //Object to hold and execute querys	
	protected ResultSet result; //Set of results of an executed query	

	protected DataAccessObject() {
		setupConnectionObjects();
	}
	
	/**
	 * Invalidates PreparedStatements and ResultSet from previous database operations
	 * and open new Connection
	 */
	protected void setupConnectionObjects(){
		this.statement = null;
		this.result = null;
		try {
			if(this.connection == null || this.connection.isClosed()){
				this.connection = new ConnectionFactory().getConnection();
			} else {
				//nothing to do, connection already established
			}
		}catch(SQLException exception){
			throw new RuntimeException("Error processing SQL - setupConnectionObjects in DataAccessObject: "
					+exception.getMessage());
		}
	}

	/**
	 * Close PreparedStatements, ResultSet and database Connection
	 */
	protected void closeConnectionObjects(){
		try {
			if(this.connection != null) {
				connection.close();
			} else {
				//nothing to do
			}

			if(this.statement != null) {
				this.statement.close();
			} else {
				//nothing to do
			}

			if(this.result != null){
				this.result.close();
			} else {
				//nothing to do
			}
		} catch(SQLException exception){
			throw new RuntimeException("Error processing SQL - closeConnectionObjects in DataAccessObject: "
					+exception.getMessage());
		}
	}
}
