package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Product> getList() throws SQLException {
		
		String sql = "select * from product";

		PreparedStatement stmt = con.prepareStatement(sql);
		ArrayList<Product> productList = new ArrayList<Product>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 
				Product product = new Product();
				
				product.setId(Integer.parseInt(rs.getString("idproduct")));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(Double.parseDouble(rs.getString("price")));
				product.setImgUrl(rs.getString("image"));
			
				productList.add(product);
			}

		} finally { 
			if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {} 
			if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {} 
			if (this.con != null) try { this.con.close(); } catch (SQLException logOrIgnore) {} 
		} 
		return productList; 
   }
	
}
