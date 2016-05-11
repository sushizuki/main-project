/** 
*    Connection.java to define Connection 
*    Define default interface for DataAccessObjects handling database connection 
*/ 

package dao;

interface Connection {
	/**
	 * Establishes a connection with a database
	 * @return SQL Connection object
	 */
	public java.sql.Connection getConnection();
}
