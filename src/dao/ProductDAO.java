package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Product;

public class ProductDAO {
	private Connection con;

	public ProductDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void insert(Product product) {
		String sql = "insert into product " +
		"(name,description,price,image,category)" +
		" values (?,?,?,?,?)";
		try {
		// prepared statement for insertion
		PreparedStatement stmt = con.prepareStatement(sql);
		
		// set values for each '?'
		stmt.setString(1, product.getName());
		stmt.setString(2, product.getDescription());
		stmt.setString(3, String.valueOf(product.getPrice()));
		stmt.setString(4, product.getImgUrl());
		stmt.setString(5, product.getCategory());
		
		// execute
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	public List<Product> getList() throws SQLException {
		
		String sql = "select * from product";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<Product> productList = new ArrayList<Product>(); 

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
				product.setCategory(rs.getString("category"));
			
				productList.add(product);
			}
			
			rs.close();
			stmt.close();
			return productList; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
   }
	
	public Product getProductById(int productId) {
        Product product = new Product();
        String sql = "select * from product where idProduct=?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
				product.setId(Integer.parseInt(rs.getString("idproduct")));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(Double.parseDouble(rs.getString("price")));
				product.setImgUrl(rs.getString("image"));
				product.setCategory(rs.getString("category"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return product;
    }
	
	public void update(Product product) {
		String sql = "update product set name=?, description=?, price=?," +
		"image=? category=? where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getImgUrl());
			stmt.setString(5, product.getCategory());
			stmt.setInt(6, product.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int productID) {
		String sql = "delete from product where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, productID);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
