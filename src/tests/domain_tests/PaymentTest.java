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
	private String paymentType;
	private String changeOfPayment;

	@Before
	public void setUp() throws EmptyFieldException, InvalidFormatException {
		paymentType = null;
		changeOfPayment = null;
	}
	
	@Test
	public void testGetPayment() throws EmptyFieldException, InvalidFormatException{
		payment = new Payment();
		assertEquals(this.paymentType, payment.getPaymentType());
		assertEquals(this.changeOfPayment, payment.getChange());
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testOnlyNumbersChange() throws  InvalidFormatException, SQLException{
		
		String invalidChange = "Jessica";
		payment = new Payment(0, paymentType, invalidChange);
	}
	
	/*
	@Test //é pra testar dessa forma o tipo de pagamento?
	public void testValidPaymentType() throws SQLException, InvalidFormatException{
		
		String validPaymentType = "2";
		
		payment = new Payment(0, validPaymentType, this.changeOfPayment);
		assertEquals(validPaymentType, payment.getPaymentType());
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testEmptyPaymentType() throws InvalidFormatException, EmptyFieldException, SQLException{
		
		String validPaymentType = "";
		
		payment = new Payment(0, validPaymentType, this.changeOfPayment);
	}
	
	@Test
	public void testValidChangeOfPayment() throws SQLException, InvalidFormatException{
		
		String validChangeOfPayment = "2.0";
		
		payment = new Payment(0, "1", validChangeOfPayment);
		assertEquals(validChangeOfPayment, payment.getChange());
	}
	*/
}
