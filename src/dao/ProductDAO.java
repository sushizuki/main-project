/** 
*    ProductDAO.java to define ProductDAO 
*    {purpose} 
*/ 

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
	}
	
	public void insert(Product product) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "insert into product " +
		"(name,description,price,image,Category_idCategory)" +
		" values (?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
		// prepared statement for insertion
		stmt = con.prepareStatement(sql);
		
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
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}	
	
	public List<Product> getList() throws SQLException {
		this.con = new ConnectionFactory().getConnection();
		
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
			
			return productList; 

		}catch (Exception e) {
			throw new RuntimeException("ERROR LISTING PRODUCTS: "+e.getMessage());
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
   }
	
	public Product getProductById(int productId) {
		this.con = new ConnectionFactory().getConnection();
        
		Product product = new Product();
        String sql = "select * from product where idProduct=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, productId);
            rs = stmt.executeQuery();

            if (rs.next()) {
				product.setId(Integer.parseInt(rs.getString("idproduct")));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(Double.parseDouble(rs.getString("price")));
				product.setImgUrl(rs.getString("image"));
				product.setCategory(rs.getString("Category_idCategory"));
            }
            
        } catch (SQLException e) {
			throw new RuntimeException("ERROR GETTING PRODUCT: "+e.getMessage());
        }finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
        
        return product;
    }
	
	public void update(Product product) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "update product set name=?, description=?, price=?," +
		" Category_idCategory=? where idProduct=?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getCategory());
			stmt.setInt(5, product.getId());
			stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}
	
	public void updateImage(Product product) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "update product set image=? where idProduct=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getImgUrl());
			stmt.setInt(2, product.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
        }
	}
	
	public void delete(Product product) {
		this.con = new ConnectionFactory().getConnection();
		
		String sql = "delete from product where idProduct=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, product.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//Convert file path to delete
			Path path = convertImagePath(product);
			
			//delete product image
			deleteImageProduct(path);
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e){}
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
		this.con = new ConnectionFactory().getConnection();

		String sql = "select name from category";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<String> categoryList = new ArrayList<String>(); 

		ResultSet rs = null;
		
		try { 	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 			
				categoryList.add(rs.getString("name"));
			}
			
			return categoryList; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
	}	

	public List<String> getProductExtraList() throws SQLException{
		this.con = new ConnectionFactory().getConnection();

		String sql = "select name from extra";

		PreparedStatement stmt = con.prepareStatement(sql);
		List<String> extraList = new ArrayList<String>(); 

		ResultSet rs = null;
		
		try { 
	    	   
			rs = stmt.executeQuery(sql);       

			while (rs.next()) { 			
				extraList.add(rs.getString("name"));
			}
			
			return extraList; 

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if(this.con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(SQLException e){}
        }
	}	
}
