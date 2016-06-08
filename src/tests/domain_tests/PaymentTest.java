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
	
	private String paymentType;
	private String changeOfPayment;
	
	@Before
	public void setUp() throws EmptyFieldException, InvalidFormatException {
		paymentType = null;
		changeOfPayment = null;
	}
	
	@Test
	public void testGetPayment() throws EmptyFieldException, InvalidFormatException, SQLException{
		Payment payment = new Payment();
		assertEquals(paymentType, payment.getPaymentType());
		assertEquals(changeOfPayment, payment.getChange());
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testOnlyNumbersChange() throws  InvalidFormatException, SQLException{
		Payment payment;
		String invalidChange = "Jessica";
		payment = new Payment(0, paymentType, invalidChange);
	}
}
