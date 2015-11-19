package domain;

import java.util.Calendar;

public class Collect implements Receiving {
	
	public static final Address sushizukiLocation = new Address(0,"72000-000", "SMPW Quadra 26 Conjunto 6", "Fim do mundo"); 

	private Address address;
	private Calendar time;
	
	public Collect(Calendar c) {
		setAddress(sushizukiLocation);
		setTime(c);
	}
	
	public Collect(){
		setAddress(sushizukiLocation);		
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
