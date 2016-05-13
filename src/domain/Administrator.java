/**
*    Administrator.java to define Administrator
*    Administrator is a type of User said to have higher privileges for
*    accessing the sub directory /adm/*.
*/

package domain;

public class Administrator extends User {

	private int idAdministrator;

	public int getId() {
		return idAdministrator;
	}

	public void setId(int id) {
		assert id > 0: "Invalid Administrator ID";
		
		this.idAdministrator = id;
	}

}
