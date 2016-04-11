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
		assert(address.getAddress() != null);
		assert(address.getAddress() != "");
		assert(address.getCep() != null);
		assert(address.getCep() != "");
		assert(address.getComplement() != null);
		assert(address.getComplement() != "");
		assert(address.getId() > 0);
		
		this.setAddress(address);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id > 0);
		
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address a) {
		assert(a != null);
		assert(a.getAddress() != null);
		assert(a.getAddress() != "");
		assert(a.getCep() != null);
		assert(a.getCep() != "");
		assert(a.getComplement() != null);
		assert(a.getComplement() != "");
		assert(a.getId() > 0);
		
		this.address = a;
	}

}
