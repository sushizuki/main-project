package domain;
import java.util.List;

public class Order {

	//implements interface Receiving (Design pattern strategy)
	
	private int id;
	private Client client;
	private List<Product> items;
	private List<Additional> additionals;
	private double totalPrice;
	private String extra; //Cream cheese or green onion;
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

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
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
		for(Product p : this.getItems()){
			this.totalPrice += p.getPrice();			
		}
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String e) {
		this.extra = e;
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
