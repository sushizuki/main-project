
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

	public User(){

	}

	public int getId() {
		return idUser;
	}

	public void setId(int id) {
		assert id != 0: "Invalid User ID: zero value cannot be accepted!";
		assert id > 0: "Invalid User ID: negative value cannot be accepted!";
		this.idUser = id;
	}

	public String getName() {
		return nameOfUser;
	}

	public void setName(String name) {
		assert name != null: "Invalid user name: null value cannot be accepted!";
		this.nameOfUser = name;
	}

	public String getEmail() {
		return emailOfUser;
	}

	public void setEmail(String email) {
		assert email != null: "Invalid user email: null value cannot be accepted!";
		this.emailOfUser = email;
	}

	public String getPassword() {
		return passwordUser;
	}

	public void setPassword(String password) {
		assert password != null: "Invalid user password: null value cannot be accepted!";
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
