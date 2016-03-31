/** 
*    Delivery.java to define Delivery 
*    {purpose} 
*/ 

package domain;

import java.util.Calendar;

public class Delivery implements Receiving {
	
	private Address address;
	private Calendar time;

	public Delivery(Address a, Calendar c) {
		setAddress(a);
		setTime(c);
	}
	
	public Delivery(Address a) {
		setAddress(a);
	}

	@Override
	public void setAddress(Address a) {
		this.address = a;
	}

	@Override
	public void setTime(Calendar c) {
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
