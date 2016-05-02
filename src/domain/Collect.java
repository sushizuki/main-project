/**
*    Collect.java to define Collect
*    When setting an Order retrieving, Collect define details for the User Collect.
*/

package domain;

import java.util.Calendar;

public class Collect implements Receiving {

	public static final Address SUSHIZUKI_LOCATION = new Address(0,"72000-000", "SMPW Quadra 26 Conjunto 6 Lote 5 casa D", "Condomínio Recanto da Primavera");

	private Address address;
	private Calendar time;

	public Collect(Calendar c) {
		setAddress(SUSHIZUKI_LOCATION);
		setTime(c);
	}

	public Collect(){
		setAddress(SUSHIZUKI_LOCATION);
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
