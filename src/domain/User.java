/**
*    User.java to define User
*    Abstraction to define any kind of person using Sushizuki
*/
package domain;

import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;
import exceptions.Validation;


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

	public void setId(int id) throws InvalidFormatException, EmptyFieldException {
		assert id > 0: "Invalid User ID";
		
		
		this.idUser = id;
	}

	public String getName() {
		return nameOfUser;
	}

	public void setName(String name) throws InvalidFormatException, EmptyFieldException  {
		assert name != null: "Invalid User name: null value cannot be accepted";
		
		if(Validation.isNotEmpty(name)){
			if(Validation.containsOnlyLettersAndSpaces(name)){
				this.nameOfUser = name;
			}else{
				throw new InvalidFormatException("Nomes devem conter apenas caracteres alfabéticos!");
			}
		}else{
				throw new EmptyFieldException("Nome não pode estar vazio!");
		}
	}

	public String getEmail() {
		return emailOfUser;
	}

	public void setEmail(String email) throws EmptyFieldException, InvalidFormatException{
		assert email != null: "Invalid User email: null value cannot be accepted";
		
		if (Validation.isNotEmpty(email)){
			if(Validation.isValidEmail(email)){
				this.emailOfUser = email;
			}else{
				throw new InvalidFormatException("Formato de e-mail inválido!");
			}
		}else{
				throw new EmptyFieldException("Email não pode esta vazio!");
		}
	}

	public String getPassword() {
		return passwordUser;
	}

	public void setPassword(String password) throws EmptyFieldException {
		assert password != null: "Invalid User password: null value cannot be accepted";
		
		if (Validation.isNotEmpty(password)){
			this.passwordUser = password;
		}else{
			throw new EmptyFieldException("Senha não pode esta vazia!");
		}
	}

	public String getPhone() {
		return phoneUser;
	}

	public void setPhone(String phone) throws  InvalidFormatException, EmptyFieldException{
		assert phone != null: "Invalid User phone: null value cannot be accepted";
		
		if(Validation.isNotEmpty(phone)){
			if( Validation.containsOnlyNumbers(phone)){
				this.phoneUser = phone;
			}else{
				throw new InvalidFormatException("Telefone deve conter apenas números!");
			}
		}else{
			throw new EmptyFieldException("Telefone não pode estar vazio!");
		}
	}
}
