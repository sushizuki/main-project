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
		assert(id>0); 
		assert(name != null); 
		this.idAdditional = id;
		this.nameAdditional = name;
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return idAdditional;
	}

	public void setId(int id) {
		assert(id>0);
		this.idAdditional = id;
		this.id = id;
	}

	public String getName() {
		return nameAdditional;
	}

	public void setName(String name) {
		assert(name != null);
		this.nameAdditional = name;
	}
}
