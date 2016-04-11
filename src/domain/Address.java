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
		assert(id != 0);
		assert(id > 0);
		assert(cep != null);
		assert(cep != "");
		assert(address != null);
		assert(address != "");
		assert(complement != null);
		assert(complement != "");
		this.setId(id);
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Address(String cep, String address, String complement) {
		assert(cep != null);
		assert(address != null);
		assert(complement != null);
		this.setCep(cep);
		this.setComplement(complement);
		this.setAddress(address);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		assert(id != null);
		assert(id > 0);
		this.id = id;
	}


	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		assert(cep != null);
		assert(cep != "");
		this.cep = cep;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		assert(address != null);	
		assert(address != "");
		this.address = address;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		assert(complement != null);
		assert(complement != "");
		this.complement = complement;
	}

	public String toString(){
		return this.address+" "+". Complemento: "+this.complement+". CEP: "+this.cep;
	}
}
