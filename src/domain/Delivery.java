/**
*    Delivery.java to define Delivery
*    When setting an Order retrieving, Delivery define details for the User Delivery.
*/

package domain;

import java.util.Calendar;

public class Delivery implements Receiving {

	private Address address;
	private Calendar dateTime;

	public Delivery(Address address, Calendar dateTime) {
		setAddress(address);
		setDateTime(dateTime);
	}

	public Delivery(Address address) {
		setAddress(address);
	}

	@Override
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public Address getAddress() {
		return this.address;
	}

	@Override
	public Calendar getDateTime() {
		return this.dateTime;
	}

}
