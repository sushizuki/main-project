/**
*    User.java to define User
*    Abstraction to define any kind of person using Sushizuki
*/

package domain;

import exceptions.UserExceptions;

public abstract class User{

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
		assert id > 0: "Invalid User ID";
		this.idUser = id;
	}

	public String getName() {
		return nameOfUser;
	}

	public void setName(String name) throws UserExceptions {
		assert name != null: "Invalid User name: null value cannot be accepted";
		
		if(Validation.containsOnlyLetters(name) && Validation.isNotEmpty(name)){
			this.nameOfUser = name;
		}else{
			throw new UserExceptions("Nomes devem conter apenas caracteres alfabéticos e não podem estar vazios!");
		}
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

	public void setPassword(String password) throws UserExceptions {
		assert password != null: "Invalid User password: null value cannot be accepted";
		
		if (Validation.isNotEmpty(password)){
			this.passwordUser = password;
		}else{
			throw new UserExceptions("Senha não pode esta vazia!");
		}
	}

	public String getPhone() {
		return phoneUser;
	}

	public void setPhone(String phone) throws UserExceptions {
		assert phone != null: "Invalid User phone: null value cannot be accepted";
		
		if(Validation.containsOnlyNumbers(phone) && Validation.isNotEmpty(phone)){
			this.phoneUser = phone;
		}else{
			throw new UserExceptions("Telefone não pode estar vazio e deve conter apenas números!");
		}
	}
}
