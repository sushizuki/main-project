public class Usuario {

	public Usuario() {
		super();
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.senha = senha;
		this.telefone = telefone;
	}

	private String nome;

	private String email;

	private String endereco;

	private String senha;

	private String telefone;

	private void realizarLogin() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
