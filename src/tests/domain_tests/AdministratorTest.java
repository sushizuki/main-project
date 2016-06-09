package tests.domain_tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import domain.Administrator;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class AdministratorTest {
	
	Administrator administrator; 

	@Before
	public void setUp() throws Exception {
		
		administrator = new Administrator();
	}

	@Test
	public void testGetAdministratorId() {
		
		int validId = 001; 
		
		administrator.setId(validId);
		assertEquals(validId, administrator.getId());
		
	}

}
