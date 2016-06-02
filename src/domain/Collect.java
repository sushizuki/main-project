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
	private Address sushizuki_location;
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
				this.sushizuki_location = new Address(0,"72000-000", "SMPW Quadra 26 Conjunto 6 Lote 5 casa D",
													  "Condomínio Recanto da Primavera");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public Address getAddress() {
		return this.sushizuki_location;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";
		
		this.dateTime = dateTime;
	}

}
