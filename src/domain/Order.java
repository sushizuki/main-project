package domain;
import java.util.HashMap;
import java.util.List;

public class Order {

	//implements interface Receiving (Design pattern strategy)
	
	private int id;
	private Client client;
	private HashMap<Product, Integer> items;
	private List<Additional> additionals;
	private double totalPrice;
	private Receiving receiving;
	private Payment payment;

	//Composition - Payment and Receiving must be an instance only here
	public Order(){
		//NEW PAYMENT
		//NEW COLLECT OR DELIVERY
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Product, Integer> items) {
		this.items = items;
	}

	public List<Additional> getAdditionals() {
		return additionals;
	}

	public void setAdditionals(List<Additional> additionals) {
		this.additionals = additionals;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setTotalPrice() {
		for (HashMap.Entry<Product, Integer> entry : this.getItems().entrySet()) {
		    Product p = entry.getKey();
		    int qtd = entry.getValue();
		    this.totalPrice += p.getPrice()*qtd;
		}
	}

	public Receiving getReceiving() {
		return receiving;
	}	

	public void setReceiving(Receiving receiving) {
		this.receiving = receiving;
	}	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
