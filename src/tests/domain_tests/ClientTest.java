package tests.domain_tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.Client;

public class ClientTest {

	private Client client;
	@Before
	public void setUp() throws Exception {
		
		this.client = new Client("Pedro","pedro@gmail.com","9999","999999999"); 
	}

	@Test
	public void testGetAndSetClientId() {
		int validId = 001;
		this.client.setId(validId);
		Assert.assertEquals(validId, this.client.getId());

	}

}
