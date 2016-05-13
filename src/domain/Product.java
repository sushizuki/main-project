
/**
*    Product.java to define Product
*    Product is an item shown to the Client to be bought.
*/

package domain;

import java.util.List;

import dao.ProductDAO;

public class Product {

	private int idProduct;
	private String nameProduct;
	private String descriptionOfProduct;
	private double priceOfProduct;
	private String imageURL;
	private String categoryOfProduct;
	private String extra; //Cream cheese or green onion

	public Product(int id, String name, String description, double price,
			String imageURL, String category, String extra) {
		assert id > 0: "Invalid Product ID";
		assert name != null: "Invalid Product name: null value cannot be accepted";
		assert price >= 0: "Invalid Product price: negative price is not allowed";
		
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImageURL(imageURL);
		this.setCategory(category);
		this.setExtra(extra);
	}

	public Product(String name, String description, double price, String imageURL,
			String category, String extra) {
		assert name != null: "Invalid Product name: null value cannot be accepted";
		assert price >= 0: "Invalid Product price: negative price is not allowed";
		
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImageURL(imageURL);
		this.setCategory(category);
		this.setExtra(extra);
	}

	public int getId() {
		return idProduct;
	}

	public void setId(int id) {
		assert id != 0: "Invalid Product ID: zero value cannot be accepted!";
		assert id > 0: "Invalid Product ID: negative value cannot be accepted!";
		this.idProduct = id;
	}

	public double getPrice() {
		return priceOfProduct;
	}

	public void setPrice(double price) {
		assert price != 0: "Invalid Product Price: zero value cannot be accepted!";
		assert price > 0: "Invalid Product Price: negative value cannot be accepted!";
		this.priceOfProduct = price;
	}

	public String getDescription() {
		return descriptionOfProduct;
	}

	public void setDescription(String description) {
		assert description != null: "Invalid description: null value cannot be accepted!";
		this.descriptionOfProduct = description;
	}

	public String getName() {
		return nameProduct;
	}

	public void setName(String name) {
		assert name != null: "Invalid product name: null value cannot be accepted!";
		this.nameProduct = name;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getCategory() {
		return categoryOfProduct;
	}

	//Get a string, look into category list from database to assign Name proper to the number
	public void setCategory(String category) {
		assert category != null: "Invalid Product category: null value cannot be accepted";
		
		ProductDAO daoProduct = new ProductDAO();
		List<String> listOfCategory = daoProduct.getProductCategoryList();
		try {
			this.categoryOfProduct = listOfCategory.get(Integer.parseInt(category)-1);
		}catch(RuntimeException e){
			System.out.println("Error assigning category");
		}
	}

	public void setCategory(int category) {
		assert category != 0: "Invalid Product category: zero value cannot be accepted!";
		assert category > 0: "Invalid Product category: negative value cannot be accepted!";
		this.categoryOfProduct = String.valueOf(category);
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public int getCategoryId(String category) {
		assert category != null: "Invalid Product category: null value cannot be accepted";
		
		ProductDAO daoProduct = new ProductDAO();
		List<String> listOfCategory = daoProduct.getProductCategoryList();
		int returnValue=0;

		for (int i = 1; i <= listOfCategory.size(); i++) {
		    if(category.equalsIgnoreCase(this.getCategory())){
		    	returnValue= i;
		    }
		}
		return returnValue; //if not found 0 = none
	}

	public int getExtraId(String extra) {
		
		int returnValue=-1; 
		
		if(extra == null || extra.isEmpty()){
			returnValue = 0;
		}else{
			
			ProductDAO daoProduct = new ProductDAO();
			List<String> listOfExtra = daoProduct.getProductExtraList();

			for (int i = 1; i <= listOfExtra.size(); i++) {
				if(extra.equalsIgnoreCase(this.getExtra())){
					returnValue= i;
				}
			}
		returnValue=0; //if not found 0 = none
		}
		
		return returnValue;
		
	}

	public String getExtra() {
		return extra;
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public void setExtra(String extra) {
        
		if(extra != null && !extra.isEmpty()){
			ProductDAO daoProduct = new ProductDAO();
			List<String> listOfExtra = daoProduct.getProductExtraList();
			try {
				this.extra = listOfExtra.get(Integer.parseInt(extra)-1);
			}catch(RuntimeException e){
				System.out.println("Error assigning extra");
			}
        } else {
        	//do nothing
        }
	}

	public void setExtra(int extra) {
		assert extra != 0: "Invalid Product extra: zero value cannot be accepted!";
		assert extra > 0: "Invalid Product extra: negative value cannot be accepted!";
		this.extra = String.valueOf(extra);
	}

}
