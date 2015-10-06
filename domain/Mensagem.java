public class Mensagem {

	public Mensagem(Cliente remetente, String mensagem) {
		super();
		this.remetente = remetente;
		this.mensagem = mensagem;
	}

	private Cliente remetente;

	private String mensagem;

	public Cliente getRemetente() {
		return remetente;
	}

	public void setRemetente(Cliente remetente) {
		this.remetente = remetente;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
