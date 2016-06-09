package tests.domain_tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Payment;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class PaymentTest {
	
	@Test
	public void test(){	
		Assert.assertTrue(true);
	}
	
	Payment payment; 
	String paymentType;
	String changeOfPayment;
	double total;
	double value; 
	
	
	@Before
	public void setUp() throws EmptyFieldException, InvalidFormatException {
		payment = new Payment();
		paymentType = null;
		changeOfPayment = null;
		total = 100.0; 
		value = 120.0; 
	}
	
	@Test
	public void testGetPayment() throws EmptyFieldException, InvalidFormatException, SQLException{
	
		assertEquals(paymentType, payment.getPaymentType());
		assertEquals(changeOfPayment, payment.getChange());
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testOnlyNumbersChange() throws  InvalidFormatException, SQLException, EmptyFieldException{
		
		String invalidChange = "Jessica";
		payment = new Payment(0, paymentType, invalidChange);
	}
	
	@Test
	public void testValidPaymentChange() {
		
		String changeResult = "20.0"; 
		
		payment.setChange(value, total);
		assertEquals(changeResult, payment.getChange());
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testInvalidPaymentChange() throws InvalidFormatException {
		
		String invalidChange = "abc"; 
		
		payment.setChange(invalidChange);
		
	}
	
	
	@Test
	public void testValidPaymentType() throws InvalidFormatException {
		
		int validPaymentType = 1; 
		String paymentType = "1"; 
		
		payment.setPaymentType(validPaymentType);
		
		System.out.println(payment.getPaymentType());
		
		assertEquals(paymentType, payment.getPaymentType());
		
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testInvalidPaymentType() throws InvalidFormatException {
		
		int invalidPaymentType = 6; 
		
		payment.setPaymentType(invalidPaymentType);
		
	}
	
	
	
	
}
