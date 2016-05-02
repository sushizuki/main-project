/** 
 * ProductDAO.java to define ProductDAO 
 * This class persists into or from database any information about addresses
 */

package dao;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Product;

//Design pattern DAO
public class ProductDAO extends DataAccessObject{

	private String sqlQuery;	

	public ProductDAO() {
		super();
	}
	
	private void updateImage(Product product) {	
		this.sqlQuery = "update product set image=? where idProduct=?";

		setupConnectionObjects();
		
		try {
			deleteImageProduct(product);
			this.statement = this.connection.prepareStatement(this.sqlQuery);
			
			//Replace '?' characters from sql variable into statement
			this.statement.setString(1, product.getImageURL());
			this.statement.setInt(2, product.getId());
			
			this.statement.execute();
		} catch (SQLException exception) {
			throw new RuntimeException("Error processing SQL - updateImage in ProductDAO: "
					+exception.getMessage());
		} finally {
			closeConnectionObjects();
        }
	}
	
	private Path convertImagePath(Product product){
		String path = getClass().getResource("/").getPath();
		path = path.replace("WEB-INF/classes/", "");
		path = path.substring(1, path.length()); //remove first slash
		path = path.replaceAll("%20", " "); //Character space
		path = path+product.getImageURL();
		
		return Paths.get(path);
	}
	
	private void deleteImageProduct(Product product){
		Path path = convertImagePath(product);
		deleteFile(path);
	}
	
	private void deleteFile(Path path){
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

		}catch (SQLException exception) {
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
        
		Product product = null;
		int id = 0;
		String name = null;
		String description = null;
		String imageURL = null;
		String category = null;
		String extra = null;
		double price = 0;
		
        this.sqlQuery = "select * from product where idProduct=?";

        setupConnectionObjects();
        
        try {
        	this.statement = this.connection.prepareStatement(this.sqlQuery);
        	
        	//Replace '?' characters from sql variable into statement
        	this.statement.setInt(1, productId);
        	
            this.result = this.statement.executeQuery();

            if (this.result.next()) {
            	//Binding product info
				id = Integer.parseInt(this.result.getString("idproduct"));
				name = this.result.getString("name");
				description = this.result.getString("description");
				price = Double.parseDouble(this.result.getString("price"));
				imageURL = this.result.getString("image");
				category = this.result.getString("Category_idCategory");
				
				product = new Product(id, name, description, price, imageURL, category, extra);
            }
            
        } catch (SQLException exception) {
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
