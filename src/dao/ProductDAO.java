package dao;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Product;

//Design pattern DAO
public class ProductDAO {
	private Connection con;

	public ProductDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void insert(Product product) {
		String sql = "insert into product " +
		"(name,description,price,image,Category_idCategory)" +
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
				product.setCategory(rs.getString("Category_idCategory"));
			
				productList.add(product);
			}
			
			rs.close();
			stmt.close();
			return productList; 

		}catch (Exception e) {
			throw new RuntimeException("ERROR LISTING PRODUCTS: "+e.getMessage());
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
				product.setCategory(rs.getString("Category_idCategory"));

            }
            
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING PRODUCT: "+e.getMessage());
        }
        
        return product;
    }
	
	public void update(Product product) {
		String sql = "update product set name=?, description=?, price=?," +
		" Category_idCategory=? where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getCategory());
			stmt.setInt(5, product.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateImage(Product product) {
		String sql = "update product set image=? where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getImgUrl());
			stmt.setInt(2, product.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public void delete(Product product) {
		String sql = "delete from product where idProduct=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, product.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//Convert file path to delete
			Path path = convertImagePath(product);
			
			//delete product image
			deleteImageProduct(path);
		}
	}
	
	private Path convertImagePath(Product product){
		String path = getClass().getResource("/").getPath();
		path = path.replace("WEB-INF/classes/", "");
		path = path.substring(1, path.length()); //remove first slash
		path = path.replaceAll("%20", " "); //Caracter space
		path = path+product.getImgUrl();
		
		return Paths.get(path);
	}
	
	public void deleteImageProduct(Product product){
		Path path = convertImagePath(product);
		deleteImageProduct(path);
	}
	
	private void deleteImageProduct(Path path){
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
	public List<String> getProductCategoryList() throws SQLException{

		String sql = "select name from category";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<String> categoryList = new ArrayList<String>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 			
				categoryList.add(rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
			return categoryList; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	/*public void finalize() {
		try {
			if(!this.con.isClosed()){
				this.con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR, could not close connection: "+e.getMessage());
		}
	}*/

	public List<String> getProductExtraList() throws SQLException{

		String sql = "select name from extra";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<String> extraList = new ArrayList<String>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 			
				extraList.add(rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
			return extraList; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
}
