/**
*    Client.java to define Client
*    Client is a type of User that can view and order Products.
*/

package domain;

public class Client extends User {

	private int idClient;
	private Address addressClient;

	public Client(Address address){
		this.setAddress(address);
	}

	public int getId() {
		return idClient;
	}

	public void setId(int id) {
		this.idClient = id;
	}

	public Address getAddress() {
		return addressClient;
	}

	public void setAddress(Address a) {
		this.addressClient = a;
	}

}
