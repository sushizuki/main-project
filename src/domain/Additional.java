/**
*    Additional.java to define Additional
*    Additional describe an attachment to a Product
*/

package domain;

public class Additional {

	private int id;
	private String name;

	public Additional(){

	}

	public Additional(int id, String name) {
		super();
		assert(id != 0);
		assert(id > 0);
		assert(name != null);
		assert(name != "");
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id != 0);
		assert(id > 0);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		assert(name != null);
		assert(name != "");
		this.name = name;
	}
}
