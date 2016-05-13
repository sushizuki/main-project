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
		assert(id != 0);
		assert(id>0); 
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
