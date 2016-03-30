package domain;

public class Client extends User {
	
	private int id;
	private Address address;
	
	public Client(Address address){
		this.setAddress(address);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address a) {
		this.address = a;
	}

}
