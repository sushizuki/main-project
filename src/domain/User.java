
/**
*    User.java to define User
*    Abstraction to define any kind of person using Sushizuki
*/

package domain;

public abstract class User {

	private int idUser;
	private String nameOfUser;
	private String emailOfUser;
	private String passwordUser;
	private String phoneUser;

	public User(){	}

	public int getId() {
		return idUser;
	}

	public void setId(int id) {
		assert id > 0: "Invalid User ID";
		
		this.idUser = id;
	}

	public String getName() {
		return nameOfUser;
	}

	public void setName(String name) {
		assert name != null: "Invalid User name: null value cannot be accepted";
		
		this.nameOfUser = name;
	}

	public String getEmail() {
		return emailOfUser;
	}

	public void setEmail(String email) {
		assert email != null: "Invalid User email: null value cannot be accepted";
		
		this.emailOfUser = email;
	}

	public String getPassword() {
		return passwordUser;
	}

	public void setPassword(String password) {
		assert password != null: "Invalid User password: null value cannot be accepted";
		
		this.passwordUser = password;
	}

	public String getPhone() {
		return phoneUser;
	}

	public void setPhone(String phone) {
		assert phone != null: "Invalid user phone: null value cannot be accepted!";
		this.phoneUser = phone;
	}
}
