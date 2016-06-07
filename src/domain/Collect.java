/**
*    Collect.java to define Collect
*    When setting an Order retrieving, Collect define details for the User Collect.
*/

package domain;

import java.util.Calendar;

import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;

public class Collect implements Receiving {
	//Administrator address
	private Address sushizukiLocation;
	private Calendar dateTime;

	public Collect(Calendar dateTime) throws EmptyFieldException, InvalidFormatException {
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";
		
		setAddress();
		setDateTime(dateTime);
	}

	public Collect() throws EmptyFieldException, InvalidFormatException{
		setAddress();
	}

	public void setAddress() throws EmptyFieldException, InvalidFormatException {
		try {
			this.sushizukiLocation = new Address(0,"71745000", "SMPW", "Park Way");
		} catch (Exception addressParametersException) {
			addressParametersException.printStackTrace();
		}
	}

	public Address getAddress() {
		return this.sushizukiLocation;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";
		
		this.dateTime = dateTime;
	}

}
