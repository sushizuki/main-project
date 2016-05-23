package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.User;
import exceptions.EmptyFieldException;
import domain.Address;
import domain.Client;

public class UserTest {
	
	Client client; 
	String email;
	String password; 
	String phone; 

	@Before
	public void setUp() throws EmptyFieldException {
		
		email ="pereirasallan@gmail.com";
		password= "password123";
		phone="+55 61 86689364";
		
	}

	@Test
	public void testValidUserName() throws EmptyFieldException{
		String validClientName = "Allan Pereira";
		
		try {
			
			client = new Client(validClientName,email, password, phone);
				
			assertEquals(validClientName, client.getName());
		} 
		catch (EmptyFieldException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}
		
		
	}

	@Test(expected = EmptyFieldException.class)
	public void testEmptyClientName() throws EmptyFieldException{
		
		String validClientName = "";
		
		client = new Client(validClientName,email, password, phone);
	}
	
	@Test(expected = Exception.class)
	public void testNullClientName() throws EmptyFieldException{
		
		String validClientName = "";
		
		client = new Client(validClientName,email, password, phone);
	}
	
}
