import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class Pedido {

	public Pedido(Cliente cliente, ArrayList<Item> itens, ArrayList<Adicional> adicionais, double valor,
			String enderecoEntrega, Calendar horarioEntrega, Collection<Item> item, Collection<Adicional> adicional) {
		super();
		this.cliente = cliente;
		this.itens = itens;
		this.adicionais = adicionais;
		this.valor = valor;
		this.enderecoEntrega = enderecoEntrega;
		this.horarioEntrega = horarioEntrega;
		this.item = item;
		this.adicional = adicional;
	}

	private Cliente cliente;

	private ArrayList<Item> itens;

	private ArrayList<Adicional> adicionais;

	private double valor;

	private String enderecoEntrega;

	private Calendar horarioEntrega;

	private Collection<Item> item;

	private Collection<Adicional> adicional;

	private void emitirConfirmacao() {

	}

	private void agendarRecebimento() {

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}

	public ArrayList<Adicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(ArrayList<Adicional> adicionais) {
		this.adicionais = adicionais;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Calendar getHorarioEntrega() {
		return horarioEntrega;
	}

	public void setHorarioEntrega(Calendar horarioEntrega) {
		this.horarioEntrega = horarioEntrega;
	}

	public Collection<Item> getItem() {
		return item;
	}

	public void setItem(Collection<Item> item) {
		this.item = item;
	}

	public Collection<Adicional> getAdicional() {
		return adicional;
	}

	public void setAdicional(Collection<Adicional> adicional) {
		this.adicional = adicional;
	}

}
