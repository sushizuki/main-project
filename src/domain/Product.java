
/**
*    Product.java to define Product
*    Product is an item shown to the Client to be bought.
*/

package domain;

import java.util.List;

import dao.ProductDAO;
import exceptions.EmptyFieldException;
import exceptions.InvalidValueException;
import exceptions.Validation;

public class Product {

	private int idProduct;
	private String nameProduct;
	private String descriptionOfProduct;
	private double priceOfProduct;
	private String imageURL;
	private String categoryOfProduct;
	private String extra; //Cream cheese or green onion

	public Product(int id, String name, String description, double price,
			String imageURL, String category, String extra) throws InvalidValueException, EmptyFieldException {
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
			String category, String extra) throws InvalidValueException, EmptyFieldException {
		System.out.println("Name no construtor:"+name);
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
		this.idProduct = id;
	}

	public double getPrice() {
		return priceOfProduct;
	}

	public void setPrice(double price) throws InvalidValueException {
		if(Validation.isPositive(price)){
			this.priceOfProduct = price;
		}else{
			throw new InvalidValueException("O preço do produto precisa ser positivo");
		}
	}

	public String getDescription() {
		return descriptionOfProduct;
	}

	public void setDescription(String description) throws EmptyFieldException {
		if (Validation.isNotEmpty(description)){
			this.descriptionOfProduct = description;
		}else{
			throw new EmptyFieldException("Descrição não pode estar vazia!");
		}
	}

	public String getName() {
		return nameProduct;
	}

	public void setName(String name) throws EmptyFieldException {
		if (Validation.isNotEmpty(name)){
			this.nameProduct = name;
		}else{
			throw new EmptyFieldException("Nome do Produto não pode estar vazio!");
		}
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) throws EmptyFieldException {
		if (Validation.isNotEmpty(imageURL)){
			this.imageURL = imageURL;
		}else{
			throw new EmptyFieldException("A URL da imagem do Produto não pode estar vazia!");
		}
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
				System.out.println("Error assigning product extra");
			}
        } else {
        	//do nothing
        }
	}

	public void setExtra(int extra) {
		this.extra = String.valueOf(extra);
	}

}
