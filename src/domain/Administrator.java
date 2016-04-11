/**
*    Administrator.java to define Administrator
*    Administrator is a type of User said to have higher privileges for
*    accessing the sub directory /adm/*.
*/

package domain;

public class Administrator extends User {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id != 0);
		this.id = id;
	}

}
