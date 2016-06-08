/**
*    Address.java to define Address
*    Describe a complete User address.
*/

package domain;

import exceptions.EmptyFieldException;
import exceptions.InvalidFormatException;
import exceptions.Validation;

public class Address{

	private Integer idAdress;
	private String cep;
	private String addressToDelivery;
	private String complement;

	public Address(){
		
	}
	
	public Address(int id, String cep, String address, String complement) throws EmptyFieldException, InvalidFormatException {
		assert id > 0: "Invalid Adddress ID";
		assert address != null: "Invalid Adddress: null value cannot be accepted";
		
		this.setId(id);
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Address(String cep, String address, String complement) throws EmptyFieldException, InvalidFormatException {
		assert address != null: "Invalid Adddress: null value cannot be accepted";
		
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Integer getId() {		
		return this.idAdress;
	}

	public void setId(Integer id) {
		assert id > 0 : "Address ID value inconsistent";
		
		this.idAdress = id;
	}


	public String getCep() {
		assert (this.cep.length() == 8 || this.cep.length() == 9)
				: "Address CEP value inconsistent";
		return this.cep;
	}

	public void setCep(String cep) throws InvalidFormatException, EmptyFieldException{
		assert (cep.length() == 8 || cep.length() == 9)
				: "Address CEP value inconsistent";
		assert (cep != null) : "Cep cannot be null.";
		
		if(Validation.isNotEmpty(cep)){
			if(Validation.containsOnlyNumbers(cep)){
				this.cep = cep;
			}else{
				throw new InvalidFormatException("Cep deve conter apenas caracteres númericos!");
			}
		}else{
			throw new EmptyFieldException("Cep não pode estar vazio!");
		}
		
	}

	public String getAddress() {
		assert this.addressToDelivery != null: "Invalid Address: null value cannot be accepted";
		
		return addressToDelivery;
	}

	public void setAddress(String address) throws EmptyFieldException {
		assert address != null: "Invalid Address: null value cannot be accepted";
		
		if(Validation.isNotEmpty(address)) {
			this.addressToDelivery = address;
		}else {
			throw new EmptyFieldException("O campo endereco nao pode ficar em branco.");
		}
		
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) throws EmptyFieldException {
		
		if(Validation.isNotEmpty(complement)) {
			this.complement = complement;
		}else {
			throw new EmptyFieldException("O campo endereco nao pode ficar em branco.");
		}
	}

	public String toString(){
		return this.addressToDelivery+" "+". Complemento: "+this.complement+". CEP: "+this.cep;
	}
}