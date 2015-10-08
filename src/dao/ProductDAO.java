package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Product;

public class ProductDAO {
	private Connection con;

	public ProductDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void insert(Product product) {
		String sql = "insert into product " +
		"(name,description,price,image)" +
		" values (?,?,?,?)";
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
		// set values for each '?'
		stmt.setString(1, product.getName());
		stmt.setString(2, product.getDescription());
		stmt.setString(3, String.valueOf(product.getPrice()));
		stmt.setString(4, product.getImgUrl());
		
		// execute
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
}
