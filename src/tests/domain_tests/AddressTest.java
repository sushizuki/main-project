package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Address;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class AddressTest {

	Address address; 
	
	private Integer idAdress;
	private String cep;
	private String addressToDelivery;
	private String complement;
	
	
	@Before
	public void setUp() throws Exception {
		address = new Address();
	}

	@Test
	public void testValidCep() throws InvalidFormatException, EmptyFieldException{
		
		String validCep = "72803360";
		
		address.setCep(validCep);
		assertEquals(validCep, address.getCep());
		
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testInvalidCep() throws InvalidFormatException, EmptyFieldException{
		
		String invalidCep = "abcdefg";
		
		address.setCep(invalidCep);
		
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testEmptyCep() throws InvalidFormatException, EmptyFieldException{
		
		String invalidCep = "";
		
		address.setCep(invalidCep);
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testNullAdditionalName() throws InvalidFormatException, EmptyFieldException{
		
		String invalidCep = null;
		
		address.setCep(invalidCep);
	}
	
	

}
