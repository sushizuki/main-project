package tests.domain_tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.Product;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;


public class ProductTest {
	private String nameProduct;
	private String descriptionOfProduct;
	private double priceOfProduct;
	private String imageURL;
	private String categoryOfProduct;
	private String extra;

	@Before
	public void setUp() throws Exception {
		this.nameProduct = "Sushi";
		this.descriptionOfProduct = "Sushi Sushi Sushi";
		this.priceOfProduct = 10.10;
		this.imageURL = "sushizukiSushi.jpg";
		this.categoryOfProduct = "1";
		this.extra = "cebolinha";
	}

	
	@Test
	public void testValidPrice() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvalidPrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidDescription() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmptyDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidName() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmptyName() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidImageURL() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmptyImageURL() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidCategoryString() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvalidCategoryString() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidCategoryId() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvalidCategoryId() {
		fail("Not yet implemented");
	}

}
