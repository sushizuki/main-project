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
		assert address != null: "Invalid Address: null value cannot be accepted";
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";
		
		setAddress(address);
		setDateTime(dateTime);
	}

	public Delivery(Address address) {
		assert address != null: "Invalid Address: null value cannot be accepted";

		setAddress(address);
	}

	public void setAddress(Address address) {
		assert address != null: "Invalid Address: null value cannot be accepted";

		this.address = address;
	}

	public void setDateTime(Calendar dateTime) {
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";	
		
		this.dateTime = dateTime;
	}

	public Address getAddress() {
		return this.address;
	}

	public Calendar getDateTime() {
		return this.dateTime;
	}
}
