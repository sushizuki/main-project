/**
*    Client.java to define Client
*    Client is a type of User that can view and order Products.
*/

package domain;

import exceptions.UserExceptions;

public class Client extends User {

	private int idClient;
	private Address addressClient;

	public Client(Address address){
		assert address != null: "Invalid Address: null value cannot be accepted";
		this.setAddress(address);
	}
	
	public Client (String nameOfUser, String emailOfUser, String passwordUser, String phoneUser) throws UserExceptions{
		
		setName(nameOfUser);
		setEmail(emailOfUser);
		setPassword(passwordUser);
		setPhone(phoneUser);
		
		
	}

	public int getId() {
		return idClient;
	}

	public void setId(int id) {
		assert id > 0: "Invalid Client ID";
		
		this.idClient = id;
	}

	public Address getAddress() {
		return addressClient;
	}

	public void setAddress(Address address) {
		assert address != null: "Invalid Address: null value cannot be accepted";
		
		this.addressClient = address;
	}

}
