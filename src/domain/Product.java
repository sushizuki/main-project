package domain;

public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private String imgUrl;
	private String category;

	public Product(int id, String name, String description, double price, String imgUrl, String categoria) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
		this.setCategory(category);
	}
	
	public Product(String name, String description, double price, String imgUrl, String categoria) {
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

	public void setCategory(String category) {
		this.category = category;
	}
	
}
