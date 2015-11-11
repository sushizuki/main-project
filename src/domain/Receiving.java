package domain;

import java.util.Calendar;

public abstract class Receiving {

	private Address address;
	private Calendar time;
		
	public void setAddress(Address a){
		this.address = a;
	}
	
	public void setTime(Calendar c){
		this.time = c;
	}
	
	public Address getAddress(){
		return this.address;
	}
	
	public Calendar getTime(){
		return this.time;
	}

}
