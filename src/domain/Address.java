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
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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
