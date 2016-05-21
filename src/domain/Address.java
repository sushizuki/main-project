/**
*    Address.java to define Address
*    Describe a complete User address.
*/

package domain;

public class Address {

	private Integer idAdress;
	private String cep;
	private String address;
	private String complement;

	public Address(){
		
	}
	
	public Address(int id, String cep, String address, String complement) {
		assert id > 0: "Invalid Adddress ID";
		assert address != null: "Invalid Adddress: null value cannot be accepted";
		
		this.setId(id);
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Address(String cep, String address, String complement) {
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

	public void setCep(String cep) {
		assert (cep.length() == 8 || cep.length() == 9)
				: "Address CEP value inconsistent";
		this.cep = cep;
	}

	public String getAddress() {
		assert this.address != null: "Invalid Address: null value cannot be accepted";
		return address;
	}

	public void setAddress(String address) {
		assert address != null: "Invalid Address: null value cannot be accepted";;
		this.address = address;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String toString(){
		return this.address+" "+". Complemento: "+this.complement+". CEP: "+this.cep;
	}
}