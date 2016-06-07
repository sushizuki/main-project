package tests.domain_tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Address;
import domain.Collect;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class CollectTest {

	@Test
	public void test(){	
		Assert.assertTrue(true);
	}
	
	Address sushizuki_location;
	Calendar dateTime;

	@Before
	public void setUp() throws EmptyFieldException, InvalidFormatException {
		this.sushizuki_location = new Address(0,"71745000", "SMPW", "Park Way");
	}
	
	@Test
	public void testGetAddress() throws EmptyFieldException, InvalidFormatException{
		Collect collect = new Collect();
		assertEquals(this.sushizuki_location.getCep(), collect.getAddress().getCep());
		assertEquals(this.sushizuki_location.getAddress(), collect.getAddress().getAddress());
		assertEquals(this.sushizuki_location.getComplement(), collect.getAddress().getComplement());
	}
}
