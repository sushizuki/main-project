package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.User;
import exceptions.UserExceptions;
import domain.Address;
import domain.Client;

public class UserTest {
	
	Client client; 
	String email;
	String password; 
	String phone; 

	@Before
	public void setUp() throws UserExceptions {
		
		email ="pereirasallan@gmail.com";
		password= "password123";
		phone="+55 61 86689364";
		
	}

	@Test
	public void testValidUserName() throws UserExceptions{
		String validClientName = "Allan Pereira";
		
		try {
			
			client = new Client(validClientName,email, password, phone);
				
			assertEquals(validClientName, client.getName());
		} 
		catch (UserExceptions exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}
		
		
	}

	@Test(expected = UserExceptions.class)
	public void testEmptyClientName() throws UserExceptions{
		
		String validClientName = "";
		
		client = new Client(validClientName,email, password, phone);
	}
	
	@Test(expected = Exception.class)
	public void testNullClientName() throws UserExceptions{
		
		String validClientName = "";
		
		client = new Client(validClientName,email, password, phone);
	}
	
}
