package tests.domain_tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import domain.Client;
import domain.Message;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class MessageTest {
	
	
	private Client sender;
	private String messageString;
	private Calendar dateSent;

	@Before
	public void setUp() throws Exception {
		
		sender = new Client("Pedro","pedro@gmail.com", "pass1234", "99999999");
		messageString = "Meu produto não chegou!";	
	}
	
	@Test
	public void testValidClient() throws InvalidFormatException, EmptyFieldException{
		
		try{
			
			Client validClient = new Client("Pedro","pedro@gmail.com", "pass1234", "99999999");
			Message message = new Message (validClient, messageString, dateSent);	
			
			assertEquals(validClient, message.getSender());
			
		}catch (InvalidFormatException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}catch (EmptyFieldException exception){
			fail("Should not throw this exception: "+exception.getMessage());
		}
	}

}
