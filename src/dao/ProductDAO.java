/** 
 * ProductDAO.java to define ProductDAO 
 * This class persists into or from database any information about products
 */

package dao;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Product;
import exceptions.EmptyFieldException;
import exceptions.InvalidValueException;

//Design pattern DAO
public class ProductDAO extends DataAccessObject{

	private String sqlQuery;	

	public ProductDAO() {
		super();
	}
	
	/*
	 * Updates image URL in database
	 * Must open new Statement and Query objects to not interfere on calling methods
	 */
	private void updateImage(Product product) throws SQLException {	
		
		assert product != null: "Invalid Product: null value cannot be accepted";
		
		String sqlQuery = "update product set image=? where idProduct=?";

		PreparedStatement statement = null;
		
		try {
			deleteImageProduct(product);
			statement = this.connection.prepareStatement(sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			statement.setString(1, product.getImageURL());
			statement.setInt(2, product.getId());
			
			statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - updateImage in ProductDAO: "
					+exception.getMessage());
		} finally {
			statement.close();
        }
	}
	
	//Converts String to Path object (URL)
	private Path convertImagePath(Product product){
		String path = getClass().getResource("/").getPath();
		path = path.replace("WEB-INF/classes/", "");
		path = path.substring(1, path.length()); //remove first slash
		path = path.replaceAll("%20", " "); //Character space
		path = path+product.getImageURL();
		
		return Paths.get(path);
	}
	
	//Deletes image from product
	private void deleteImageProduct(Product product){
		
		assert product != null: "Invalid Product: null value cannot be accepted";
		
		Path path = convertImagePath(product);
		deleteFile(path);
	}
	
	//Deletes file from disk
	private void deleteFile(Path path){
		
		assert path != null: "Invalid Path: null value cannot be accepted";
		
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
	
	/** insert registers a product into the database
	 * @param Product object containing full product details
	 */
	public void insert(Product product) {
		
		assert product != null: "Invalid Product: null value cannot be accepted";
		
		this.sqlQuery = "insert into product "
				+ "(name,description,price,image,Category_idCategory)"
				+ "values (?,?,?,?,?)";
		
		setupConnectionObjects();
		
		try {
			this.statement = this.connection.prepareStatement(sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			this.statement.setString(1, product.getName());
			this.statement.setString(2, product.getDescription());
			this.statement.setString(3, String.valueOf(product.getPrice()));
			this.statement.setString(4, product.getImageURL());
			this.statement.setString(5, product.getCategory());
			
			// execute
			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - insert in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
	}	
	
	/** getList fetch from database all  products saved
	 * @return List<Product> list of products and their details 
	 */
	public List<Product> getList() {
		
		this.sqlQuery = "select * from product";
		List<Product> productList = new ArrayList<Product>(); 
		
		setupConnectionObjects();
		
		try { 
			this.statement = this.connection.prepareStatement(this.sqlQuery);	    	   
			this.result = this.statement.executeQuery(this.sqlQuery);     
			
			int id = 0;
			String name = null;
			String description = null;
			String imageURL = null;
			String category = null;
			String extra = null;
			double price = 0;
			
			//for every product found in database
			while (this.result.next()) { 				
				id = Integer.parseInt(this.result.getString("idproduct"));
				name = this.result.getString("name");
				description = this.result.getString("description");
				price = Double.parseDouble(this.result.getString("price"));
				imageURL = this.result.getString("image");
				category = this.result.getString("Category_idCategory");
							
				productList.add(new Product(id, name, description, price, imageURL, category, extra));
			}			

		}catch (SQLException | InvalidValueException | EmptyFieldException exception) {
			throw new RuntimeException("Error processing SQL - getList in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
		}
		
		return productList; 
	}
	
	/** getProductById fetch from database all  products saved
	 * @param productId int id number to be searched
	 * @return List<Product> list of products and their details 
	 */
	public Product getProductById(int productId) {
		
		assert productId > 0: "Invalid Product ID";
        
		Product product = null;
		
        this.sqlQuery = "select * from product where idProduct=?";

        setupConnectionObjects();
        
        try {
        	this.statement = this.connection.prepareStatement(this.sqlQuery);
        	
        	//Replace '?' characters from sql variable into statement
        	this.statement.setInt(1, productId);
        	
            this.result = this.statement.executeQuery();

            if (this.result.next()) {
            	//Binding product info
				int id = Integer.parseInt(this.result.getString("idproduct"));
				String name = this.result.getString("name");
				String description = this.result.getString("description");
				double price = Double.parseDouble(this.result.getString("price"));
				String imageURL = this.result.getString("image");
				String category = this.result.getString("Category_idCategory");
				String extra = null;
				product = new Product(id, name, description, price, imageURL, category, extra);
            }
            
        } catch (SQLException | InvalidValueException | EmptyFieldException exception) {
			throw new RuntimeException("Error processing SQL - getProductById in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
        
        return product;
    }
	
	/**
	 * update product in the database
	 * @param product object containing full product details
	 */
	public void update(Product product) {
		
		assert product != null: "Invalid Product: null value cannot be accepted";
		
		this.sqlQuery = "update product set name=?, description=?, price=?,"
				+ " Category_idCategory=? where idProduct=?";		
		
		setupConnectionObjects();
		
		try {
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			this.statement.setString(1, product.getName());
			this.statement.setString(2, product.getDescription());
			this.statement.setDouble(3, product.getPrice());
			this.statement.setString(4, product.getCategory());
			this.statement.setInt(5, product.getId());
			
			this.statement.execute();	
			
			//Check for an image update
	        if(product.getImageURL() != null){
	        	updateImage(product);
	        } else {
	        	//nothing to do
	        }
			
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - update in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
	}	
	
	/**
	 * delete product from database
	 * @param product object containing full product details
	 */
	public void delete(Product product) {	
		
		assert product != null: "Invalid Product: null value cannot be accepted";
		
		this.sqlQuery = "delete from product where idProduct=?";

		setupConnectionObjects();
		
		try {
			this.statement = connection.prepareStatement(this.sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			this.statement.setInt(1, product.getId());
			
			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - delete in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
	}
	
	/** getProductCategoryList fetch from database all product categories saved
	 * @return List<String> list of product categories 
	 */
	public List<String> getProductCategoryList(){
		this.sqlQuery = "select name from category";
		List<String> categoryList = new ArrayList<String>(); 
		
		setupConnectionObjects();
		
		try { 	    	   
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			this.result = this.statement.executeQuery(this.sqlQuery);       

			while (this.result.next()) { 			
				categoryList.add(this.result.getString("name"));
			}
		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getProductCategoryList in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
		
		return categoryList; 
	}	

	/** getProductExtraList fetch from database all product extras saved
	 * @return List<String> list of product extras 
	 */
	public List<String> getProductExtraList(){
		this.sqlQuery = "select name from extra";
		List<String> extraList = new ArrayList<String>(); 
		
		setupConnectionObjects();
		
		try { 	    	   
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			this.result = this.statement.executeQuery(this.sqlQuery);       

			while (this.result.next()) { 			
				extraList.add(this.result.getString("name"));
			}		

		}catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - getProductExtraList in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
		
		return extraList; 
	}	
}
