/**
*    Collect.java to define Collect
*    When setting an Order retrieving, Collect define details for the User Collect.
*/

package domain;

import java.util.Calendar;

public class Collect implements Receiving {

	//Administrator address
	public static final Address SUSHIZUKI_LOCATION = new Address(0,"72000-000", "SMPW Quadra 26 Conjunto 6 Lote 5 casa D", "Condomínio Recanto da Primavera");

	private Address address;
	private Calendar dateTime;

	public Collect(Calendar dateTime) {
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";
		
		setAddress(SUSHIZUKI_LOCATION);
		setDateTime(dateTime);
	}

	public Collect(){
		setAddress(SUSHIZUKI_LOCATION);
	}

	@Override
	public void setAddress(Address address) {
		assert address != null: "Invalid address: null value cannot be accepted";
		
		this.address = address;
	}

	@Override
	public Address getAddress() {
		return this.address;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		assert dateTime != null: "Invalid Calendar: null value cannot be accepted";
		
		this.dateTime = dateTime;
	}

}
