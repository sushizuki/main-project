package tests.domain_tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Address;
import domain.Delivery;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class DeliveryTest {
	@Test
	public void test(){	
		Assert.assertTrue(true);
	}
	
	Address address;
	Calendar dateTime;
	
	@Before
	public void setUp() throws EmptyFieldException, InvalidFormatException {
		this.address = new Address(0,"71745777", "SQS 1505", "Apt 105");
	}
	
	@Test
	public void testGetAddress() throws EmptyFieldException, InvalidFormatException{
		Delivery delivery = new Delivery(address);
		assertEquals(this.address.getCep(), delivery.getAddress().getCep());
		assertEquals(this.address.getAddress(), delivery.getAddress().getAddress());
		assertEquals(this.address.getComplement(), delivery.getAddress().getComplement());
	}
}

