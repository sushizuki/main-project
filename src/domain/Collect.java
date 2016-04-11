/**
*    Collect.java to define Collect
*    When setting an Order retrieving, Collect define details for the User Collect.
*/

package domain;

import java.util.Calendar;

public class Collect implements Receiving {

	public static final Address sushizukiLocation = new Address(0,"72000-000", "SMPW Quadra 26 Conjunto 6 Lote 5 casa D", "Condomínio Recanto da Primavera");

	private Address address;
	private Calendar time;

	public Collect(Calendar c) {
		assert(c != null);
		setAddress(sushizukiLocation);
		setTime(c);
	}

	public Collect(){
		setAddress(sushizukiLocation);
	}

	@Override
	public void setAddress(Address a) {
		assert(a != null);
		assert(a.getAddress() != null);
		assert(a.getAddress() != "");
		assert(a.getCep() != null);
		assert(a.getCep() != "");
		assert(a.getComplement() != null);
		assert(a.getComplement() != "");
		assert(a.getId() > 0);
		
		this.address = a;
	}

	@Override
	public void setTime(Calendar c) {
		assert(c != null);
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
