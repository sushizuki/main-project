package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Address;
import domain.Client;

import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;
import exceptions.InvalidSizeException;

public class UserTest {
	
	Client client; 
	String email;
	String password; 
	String phone; 

	@Before
	public void setUp() {
		
		email ="pereirasallan@gmail.com";
		password= "password123";
		phone="+55 61 86689364";
		
	}

	@Test
	public void testValidUserName() throws InvalidFormatException, EmptyFieldException{
		String validClientName = "Allan Pereira";
		
		try {
			
			client = new Client(validClientName,email, password, phone);
				
			assertEquals(validClientName, client.getName());
		} 
		catch (InvalidFormatException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}catch (EmptyFieldException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}
		
		
	}

	@Test(expected = InvalidFormatException.class)
	public void testEmptyClientName() throws InvalidFormatException, EmptyFieldException{
		
		String validClientName = "";
		
		client = new Client(validClientName,email, password, phone);
	}
	
	@Test(expected = Exception.class)
	public void testNullClientName() throws InvalidFormatException, EmptyFieldException{
		
		String validClientName = "";
		
		client = new Client(validClientName,email, password, phone);
	}
	
}
