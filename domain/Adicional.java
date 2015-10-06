import java.util.Collection;

public class Adicional {

	public Adicional(String nome, Collection<Item> item, Collection<Pedido> pedido) {
		super();
		this.nome = nome;
		this.item = item;
		this.pedido = pedido;
	}

	private String nome;

	private Collection<Item> item;

	private Collection<Pedido> pedido;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Item> getItem() {
		return item;
	}

	public void setItem(Collection<Item> item) {
		this.item = item;
	}

	public Collection<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(Collection<Pedido> pedido) {
		this.pedido = pedido;
	}

}
