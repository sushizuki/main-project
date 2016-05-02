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
	
	protected void setupConnectionObjects(){
		this.statement = null;
		this.result = null;
		this.connection = new ConnectionFactory().getConnection();
	}

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
