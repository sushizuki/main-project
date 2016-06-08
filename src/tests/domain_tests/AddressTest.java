package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Address;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class AddressTest {

	Address address; 
	

	String cep;
	String addressToDelivery;
	String complement;
	
	
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
	
	@Test
	public void testValidAddress() throws InvalidFormatException, EmptyFieldException{
		
		String validAddress = "QNP 27 Conjunto H, 28";
		
		address.setAddress(validAddress);
		assertEquals(validAddress, address.getAddress());
		
	}
	
	
	@Test(expected = EmptyFieldException.class)
	public void testEmptyAddress() throws InvalidFormatException, EmptyFieldException{
		
		String invalidAddress = "";
		
		address.setAddress(invalidAddress);
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testNullAddress() throws InvalidFormatException, EmptyFieldException{
		
		String invalidAddress = null;
		
		address.setAddress(invalidAddress);
	}
	
	@Test
	public void testValidComplement() throws InvalidFormatException, EmptyFieldException{
		
		String validComplement = "Ceilandia Norte";
		
		address.setComplement(validComplement);
		assertEquals(validComplement, address.getComplement());
		
	}
	
	
	@Test(expected = EmptyFieldException.class)
	public void testEmptyComplement() throws InvalidFormatException, EmptyFieldException{
		
		String invalidComplement = "";
		
		address.setComplement(invalidComplement);
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testNullComplement() throws InvalidFormatException, EmptyFieldException{
		
		String invalidComplement = null;
		
		address.setComplement(invalidComplement);
	}
	
	

}
