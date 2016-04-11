/**
*    Delivery.java to define Delivery
*    When setting an Order retrieving, Delivery define details for the User Delivery.
*/

package domain;

import java.util.Calendar;

public class Delivery implements Receiving {

	private Address address;
	private Calendar time;

	public Delivery(Address a, Calendar c) {
		assert(a != null);
		assert(a.getAddress() != null);
		assert(a.getAddress() != "");
		assert(a.getCep() != null);
		assert(a.getCep() != "");
		assert(a.getComplement() != null);
		assert(a.getComplement() != "");
		assert(a.getId() > 0);assert(c != null);
		setAddress(a);
		setTime(c);
	}

	public Delivery(Address a) {
		assert(a != null);
		assert(a.getAddress() != null);
		assert(a.getAddress() != "");
		assert(a.getCep() != null);
		assert(a.getCep() != "");
		assert(a.getComplement() != null);
		assert(a.getComplement() != "");
		assert(a.getId() > 0);setAddress(a);
	}

	@Override
	public void setAddress(Address a) {
		assert(a != null);
		this.address = a;
	}

	@Override
	public void setTime(Calendar c) {
		assert(c != null);
		this.time = c;
	}

	@Override
	public Address getAddress() {
		return this.address;
	}

	@Override
	public Calendar getTime() {
		return this.time;
	}

}
