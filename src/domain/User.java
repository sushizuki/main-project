/**
*    User.java to define User
*    Abstraction to define any kind of person using Sushizuki
*/

package domain;

public abstract class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;

	public User(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		assert(email != null);
		assert(email != "");
		
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		assert(password != null);
		assert(password != "");
		
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		assert(phone != null);
		assert(phone != "");
		
		this.phone = phone;
	}
}
