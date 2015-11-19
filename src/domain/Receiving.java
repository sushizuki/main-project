package domain;

import java.util.Calendar;

public interface Receiving {
		
	public void setAddress(Address a);
	
	public void setTime(Calendar c);
	
	public Address getAddress();
	
	public Calendar getTime();

}
