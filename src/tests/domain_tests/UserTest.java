package tests.domain_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Client;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class UserTest{
	
	@Test
	public void test(){	
		Assert.assertTrue(true);
	}
	
	Client client;
	String name; 
	String email;
	String password; 
	String phone; 

	@Before
	public void setUp() {
		
		name = "Allan";
		email ="pereirasallan@gmail.com";
		password= "password123";
		phone="556186689364";
		
	}

	@Test
	public void testValidUserName() throws InvalidFormatException, EmptyFieldException{
		
		String validClientName = "Allan Pereira";
		
		try{
			
			client = new Client(validClientName,email, password, phone);
				
			assertEquals(validClientName, client.getName());
			
		}catch (InvalidFormatException exception){
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
		
		String validClientName = null;
		
		client = new Client(validClientName,email, password, phone);
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testNonLettersClientName() throws  InvalidFormatException, EmptyFieldException{
		
		String validClientName = "Allan 123";
		
		client = new Client(validClientName,email, password, phone);
	}
	
	@Test
	public void testValidClientPassword() throws EmptyFieldException, InvalidFormatException{
		
		String validPassword = "pass1234";
		
		try{
		client = new Client(name,email, validPassword, phone);
		
		}catch (InvalidFormatException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}catch (EmptyFieldException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}
	
	}
	
	@Test(expected = EmptyFieldException.class)
	public void  testEmptyClientPassword() throws  InvalidFormatException, EmptyFieldException{
		
		String validPassword = "";
		
		client = new Client(name,email, validPassword, phone);
	}
	
	@Test(expected = EmptyFieldException.class)
	public void  testNullClientPassword() throws  InvalidFormatException, EmptyFieldException{
		
		String validPassword = null;
		
		client = new Client(name,email, validPassword, phone);
	}
	
	
}


