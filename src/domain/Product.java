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

	public Product(int id, String name, String description, double price, String imgUrl, String category) throws SQLException {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
		this.setCategory(category);
	}
	
	public Product(String name, String description, double price, String imgUrl, String category) throws SQLException {
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
		this.setCategory(category);
	}
	
	public Product(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getCategory() {
		return category;
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public void setCategory(String category) throws SQLException {
		ProductDAO dao = new ProductDAO();
		List<String> list = dao.getProductCategoryList();
		try {
			this.category = list.get(Integer.parseInt(category)-1);
		}catch(RuntimeException e){
			System.out.println("Error assigning category");
		}
	}
	
	public void setCategory(int category) {
		this.category = String.valueOf(category);
	}
	
	//Get a number, look into category list from database to assign Name proper to the number
	public int getCategoryId(String category) throws SQLException {
		ProductDAO dao = new ProductDAO();
		List<String> list = dao.getProductCategoryList();
		
		for (int i = 1; i <= list.size(); i++) {
		    if(category.equalsIgnoreCase(this.getCategory())){
		    	return i;
		    }
		}
		return 0; //if not found 0 = none
	}
	
}
