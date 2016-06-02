/**
*    Additional.java to define Additional
*    Additional describes an attachment to a Product
*/

package domain;

import exceptions.InvalidFormatException;
import exceptions.Validation;

public class Additional {

	private int idAdditional;
	private String nameAdditional;

	public Additional(){

	}

	public Additional(int id, String name) {
		super();
		assert id > 0: "Invalid Additional ID"; 
		assert name != null: "Invalid Additional name: null value cannot be accepted"; 
		
		this.idAdditional = id;
		this.nameAdditional = name;
	}

	public int getId() {
		return idAdditional;
	}

	public void setId(int id){
		assert id > 0: "Invalid Additional ID"; 
		
		
		this.idAdditional = id;
	}

	public String getName() {
		return nameAdditional;
	}

	public void setName(String name) throws InvalidFormatException {
		assert name != null: "Invalid Additional name: null value cannot be accepted"; 
		
		if(Validation.containsOnlyLetters(name) && Validation.isNotEmpty(name)){
			this.nameAdditional = name;
		}else{
			throw new InvalidFormatException("Nomes devem conter apenas caracteres alfabéticos e não podem estar vazios!");
		}

	}
}
