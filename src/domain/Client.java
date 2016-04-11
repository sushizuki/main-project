/**
*    Client.java to define Client
*    Client is a type of User that can view and order Products.
*/

package domain;

public class Client extends User {

	private int id;
	private Address address;

	public Client(Address address){
		assert(address != null);
		this.setAddress(address);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id != 0);
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address a) {
		assert(a != null);
		this.address = a;
	}

}
