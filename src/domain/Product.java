/**
*    Product.java to define Product
*    Product is an item shown to the Client to be bought.
*/

package domain;

import java.sql.SQLException;
import java.util.List;

import dao.ProductDAO;

public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private String imgUrl;
	private String category;
	private String extra; //Cream cheese or green onion

	public Product(int id, String name, String description, double price, String imgUrl, String category, String extra) throws SQLException {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
		this.setCategory(category);
		this.setExtra(extra);
	}

	public Product(String name, String description, double price, String imgUrl, String category, String extra) throws SQLException {
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
		this.setCategory(category);
		this.setExtra(extra);
	}

	public Product(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id > 0);
		
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		assert(price > 0);
		
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		assert(description != null);
		assert(description != "");
		
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		assert(name != null);
		assert(name != "");
		
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		assert(imgUrl != null);
		assert(imgUrl != "");
		
		this.imgUrl = imgUrl;
	}

	public String getCategory() {
		return category;
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public void setCategory(String category) throws SQLException {
		assert(category != null);
		assert(category != "");
		
		ProductDAO dao = new ProductDAO();
		assert(dao != null);
	
		List<String> list = dao.getProductCategoryList();
		try {
			this.category = list.get(Integer.parseInt(category)-1);
		}catch(RuntimeException e){
			System.out.println("Error assigning category");
		}
	}

	public void setCategory(int category) {
		assert(category > 0);
		assert(category < 6);
		
		this.category = String.valueOf(category);
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public int getCategoryId(String category) throws SQLException {
		assert(category != null);
		assert(category != "");
		
		ProductDAO dao = new ProductDAO();
		assert(dao != null);
		
		List<String> list = dao.getProductCategoryList();

		for (int i = 1; i <= list.size(); i++) {
		    if(category.equalsIgnoreCase(this.getCategory())){
		    	return i;
		    }
		}
		return 0; //if not found 0 = none
	}

	public int getExtraId(String extra) throws SQLException {
		assert(extra != null);
		assert(extra != "");
		
		if(extra == null || extra.isEmpty()){
			return 0;
		}
		ProductDAO dao = new ProductDAO();
		assert(dao != null);
		
		List<String> list = dao.getProductExtraList();

		for (int i = 1; i <= list.size(); i++) {
		    if(extra.equalsIgnoreCase(this.getExtra())){
		    	return i;
		    }
		}
		return 0; //if not found 0 = none
	}

	public String getExtra() {
		return extra;
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public void setExtra(String extra) throws SQLException {
		assert(extra != null);
		assert(extra != "");
		
		ProductDAO dao = new ProductDAO();
		assert(dao != null);
		
		List<String> list = dao.getProductExtraList();
		try {
			this.extra = list.get(Integer.parseInt(extra)-1);
		}catch(RuntimeException e){
			System.out.println("Error assigning extra");
		}
	}

	public void setExtra(int extra) {
		assert(extra > 0);
		assert(extra < 3);
		
		this.extra = String.valueOf(extra);
	}

}
