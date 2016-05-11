/**
*    Additional.java to define Additional
*    Additional describes an attachment to a Product
*/

package domain;

public class Additional {

	private int idAdditional;
	private String nameAdditional;

	public Additional(){

	}

	public Additional(int id, String name) {
		super();
		this.idAdditional = id;
		this.nameAdditional = name;
	}

	public int getId() {
		return idAdditional;
	}

	public void setId(int id) {
		this.idAdditional = id;
	}

	public String getName() {
		return nameAdditional;
	}

	public void setName(String name) {
		this.nameAdditional = name;
	}
}
