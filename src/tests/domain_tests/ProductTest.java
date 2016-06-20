package tests.domain_tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.Product;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;
import exceptions.InvalidValueException;
import junit.framework.Assert;


public class ProductTest {
	private String nameProduct;
	private String descriptionOfProduct;
	private double priceOfProduct;
	private String imageURL;
	private String categoryOfProduct;
	private String extra;
	private Product product;

	@Before
	public void setUp() throws Exception {
		this.nameProduct = "Sushi";
		this.descriptionOfProduct = "Sushi Sushi Sushi";
		this.priceOfProduct = 10.10;
		this.imageURL = "sushizukiSushi.jpg";
		this.categoryOfProduct = "1";
		this.extra = "cebolinha";
	}

	
<<<<<<< HEAD
//	@Test
//	public void testValidFields() throws InvalidValueException, EmptyFieldException {
//		this.product = new Product(this.nameProduct, this.descriptionOfProduct, this.priceOfProduct,
//				this.imageURL, this.categoryOfProduct, this.extra);
//		assertEquals(this.nameProduct, this.product.getName());
//		assertEquals(this.descriptionOfProduct, this.product.getDescription());
//		assertEquals(this.extra, this.product.getExtra());
//		assertEquals(this.categoryOfProduct, this.product.getCategory());
//		assertEquals(this.imageURL, this.product.getImageURL());
//	}
	
	@Test(expected = InvalidValueException.class)
	public void testInvalidPrice() throws InvalidValueException, EmptyFieldException {
		double price = -1;
		this.product = new Product(this.nameProduct, this.descriptionOfProduct, price,
				this.imageURL, this.categoryOfProduct, this.extra);
	}

		
	@Test(expected = EmptyFieldException.class)
	public void testEmptyDescription() throws InvalidValueException, EmptyFieldException {
		String description = "";
		this.product = new Product(this.nameProduct, description, this.priceOfProduct,
				this.imageURL, this.categoryOfProduct, this.extra);
	}

	
	@Test(expected = EmptyFieldException.class)
	public void testEmptyName() throws InvalidValueException, EmptyFieldException {
		String name = "";
		this.product = new Product(name, this.descriptionOfProduct, this.priceOfProduct,
				this.imageURL, this.categoryOfProduct, this.extra);
	}

	
	@Test (expected = EmptyFieldException.class)
	public void testEmptyImageURL() throws InvalidValueException, EmptyFieldException {
		String url = "";
		this.product = new Product(this.nameProduct, this.descriptionOfProduct, this.priceOfProduct,
									url, this.categoryOfProduct, this.extra);
	}
	
}
