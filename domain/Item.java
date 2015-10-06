import java.util.ArrayList;

public class Item {

	public Item(double preco, String decricao, String nome, ArrayList<Adicional> adicional) {
		super();
		this.preco = preco;
		this.decricao = decricao;
		this.nome = nome;
		this.adicional = adicional;
	}

	private double preco;

	private String decricao;

	private String nome;

	private ArrayList<Adicional> adicional;

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDecricao() {
		return decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Adicional> getAdicional() {
		return adicional;
	}

	public void setAdicional(ArrayList<Adicional> adicional) {
		this.adicional = adicional;
	}

}
