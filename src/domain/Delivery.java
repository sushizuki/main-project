package domain;

import java.util.Calendar;

public class Delivery extends Receiving {

	public Delivery(Address a, Calendar c) {
		super.setAddress(a);
		super.setTime(c);
	}

}
