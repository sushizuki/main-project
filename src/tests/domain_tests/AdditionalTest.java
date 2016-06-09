package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Additional;
import domain.Client;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class AdditionalTest {
	
	String nameAdditional;
	Additional additional; 
	

	@Before
	public void setUp() throws Exception {
		
		nameAdditional = "shoyu"; 
		additional = new Additional(); 
		
	}

	@Test
	public void testValidAdditionalName() throws InvalidFormatException, EmptyFieldException{
		
		String validAdditionalName = "shoyu";
		
		try{
			
			additional.setName(validAdditionalName);
				
			assertEquals( validAdditionalName, additional.getName());
			
		}catch (InvalidFormatException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}catch (EmptyFieldException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}
	}
	
	@Test(expected = InvalidFormatException.class)
	public void testInvalidAdditionalName() throws InvalidFormatException, EmptyFieldException{
		
		String invalidAdditionalName = "123-";
		
		additional.setName(invalidAdditionalName);
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testEmptyAdditionaltName() throws InvalidFormatException, EmptyFieldException{
		
		String validAdditionalName = "";
		
		additional.setName(validAdditionalName);
	}
	
	@Test(expected = EmptyFieldException.class)
	public void testNullAdditionalName() throws InvalidFormatException, EmptyFieldException{
		
		String validAdditionalName = null;
		
		additional.setName(validAdditionalName);
	}
	
	@Test
	public void testGetAdditionalName() throws InvalidFormatException, EmptyFieldException{
		
		String validAdditionalName = "shoyu";
		additional.setName(validAdditionalName);
		assertEquals( validAdditionalName, additional.getName());
	}
	
	
	
	
	
	

}
