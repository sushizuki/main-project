/**
*    Address.java to define Address
*    Describe a complete User address.
*/

package domain;

public class Address {

	private Integer id;
	private String cep;
	private String address;
	private String complement;

	public Address(int id, String cep, String address, String complement) {
		this.setId(id);
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Address(String cep, String address, String complement) {
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Integer getId() {
		assert this.id > 0 : "Address ID value inconsistent";
		return this.id;
	}

	public void setId(Integer id) {
		assert id > 0 : "Address ID value inconsistent";
		this.id = id;
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
		assert this.address != null;
		return address;
	}

	public void setAddress(String address) {
		assert address != null;
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
