
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
		this.idUser = id;
	}

	public String getName() {
		return nameOfUser;
	}

	public void setName(String name) {
		this.nameOfUser = name;
	}

	public String getEmail() {
		return emailOfUser;
	}

	public void setEmail(String email) {
		this.emailOfUser = email;
	}

	public String getPassword() {
		return passwordUser;
	}

	public void setPassword(String password) {
		this.passwordUser = password;
	}

	public String getPhone() {
		return phoneUser;
	}

	public void setPhone(String phone) {
		this.phoneUser = phone;
	}
}
