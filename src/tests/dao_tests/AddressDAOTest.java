package tests.dao_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Address;
import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;
import dao.AddressDAO;


public class AddressDAOTest {

	Address address; 
	AddressDAO addressDao; 
	
	
	@Before
	public void setUp() throws EmptyFieldException{
		address= new Address();
		addressDao = new AddressDAO();
		
		try {
			address.setAddress("QNP 27 Conjunto H, 28");
			address.setCep("72242165");
			address.setComplement("Ceilandia Norte");
		} catch (EmptyFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		address.setId(1);
	}

	@Test
	public void testGetAddress() {
		
		try {
			assertEquals(address, addressDao.getAddress(address.getAddress()));
		} catch (EmptyFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAddressById() {
		try {
			assertEquals(address, addressDao.getAddressById(address.getId()));
		} catch (EmptyFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
