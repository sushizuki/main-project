package domain;

import java.util.Calendar;

public class Collect extends Receiving {
	
	private final Address sushizukiLocation = new Address("72000-000", "SMPW Quadra 26 Conjunto 6", "Fim do mundo"); 

	public Collect(Calendar c) {
		super.setAddress(sushizukiLocation);
		super.setTime(c);
	}

}
