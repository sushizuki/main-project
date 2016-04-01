/**
*    Receiving.java to define Receiving
*    Interface to describe the Users' methods of receiving their orders.
*/

package domain;

import java.util.Calendar;

public interface Receiving {

	public void setAddress(Address a);

	public void setTime(Calendar c);

	public Address getAddress();

	public Calendar getTime();

}
