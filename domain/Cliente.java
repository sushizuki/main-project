import java.util.ArrayList;
import java.util.Calendar;

public class Cliente extends Usuario {

	public Cliente(ArrayList<Pedido> pedido, Mensagem mensagem, Calendar dataEnviada) {
		super();
		this.pedido = pedido;
		this.mensagem = mensagem;
		this.dataEnviada = dataEnviada;
	}

	private ArrayList<Pedido> pedido;

	private Mensagem mensagem;
	
	private Calendar dataEnviada;

	public ArrayList<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(ArrayList<Pedido> pedido) {
		this.pedido = pedido;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Calendar getDataEnviada() {
		return dataEnviada;
	}

	public void setDataEnviada(Calendar dataEnviada) {
		this.dataEnviada = dataEnviada;
	}

}
