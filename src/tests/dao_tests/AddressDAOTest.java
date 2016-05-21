package tests.dao_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Address;
import dao.AddressDAO;


public class AddressDAOTest {

	Address address; 
	AddressDAO addressDao; 
	
	
	@Before
	public void setUp(){
		address= new Address();
		addressDao = new AddressDAO();
		
		address.setAddress("QNP 27 Conjunto H, 28");
		address.setCep("72242165");
		address.setComplement("Ceilandia Norte");
		address.setId(1);
	}

	@Test
	public void testGetAddress() {
		
		assertEquals(address, addressDao.getAddress(address.getAddress()));
	}

	@Test
	public void testGetAddressById() {
		assertEquals(address, addressDao.getAddressById(address.getId()));
	}

}
