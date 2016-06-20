package tests.domain_tests;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Additional;
import domain.Client;
import domain.Order;
import domain.Payment;
import domain.Product;
import domain.Receiving;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;
import exceptions.InvalidValueException;

public class OrderTest {

//	@Test
//	public void test(){	
//		Assert.assertTrue(true);
//	}
	
	private Client client;
	private HashMap<Product, Integer> items;
	private List<Additional> additionalsList;
	private double totalPrice;
	private Receiving receiving; 
	private Payment paymentOfOrder;
	private String statusOfOrder;

	@Before
	public void setUp() throws InvalidFormatException, EmptyFieldException, InvalidValueException {
		this.client = new Client("Jessica", "suzukijessica@gmail.com", "senhasenha", "33333333");
		Product product = new Product("temaki", "temaki de camarao", 15.00, "img/temakiCamarao.jpg", "temaki", "cebolinha");
		this.items.put(product, 0);
		Additional additional = new Additional();
		this.additionalsList.add(additional);
		this.totalPrice = 25.50;
		this.paymentOfOrder = new Payment(); 
		this.statusOfOrder = "Entregue";
	}

//	@Test
//	public void testValidUserName(){
//		Order order = new Order();
//		order.setClient(this.client);
//		Assert.assertEquals(this.client, order.getClient());
//	}
}
