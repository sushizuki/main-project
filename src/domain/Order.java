/**
*    Order.java to define Order
*    Order describes a set of Products and other details of a Client's order. 
*/

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
	private String status;

	//Composition - Payment and Receiving must be an instance only here
	public Order(){
		//NEW PAYMENT
		//NEW COLLECT OR DELIVERY
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id > 0);
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		assert(client != null);
		assert(client.getAddress() != null);
		assert(client.getAddress() != "");
		assert(client.getEmail() != null);
		assert(client.getEmail() != "");
		assert(client.getId() > 0);
		assert(client.getName() != null);
		assert(client.getName() != "");
		assert(client.getPhone() != null);
		assert(client.getName() != "");

		this.client = client;
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Product, Integer> items) {
		assert(items != null);
		this.items = items;
	}

	public List<Additional> getAdditionals() {
		return additionals;
	}

	public void setAdditionals(List<Additional> additionals) {
		assert(additionals != null);
		this.additionals = additionals;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		assert(totalPrice > 0);
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
		assert(receiving != null);
		assert(receiving.getAddress() != null);
		assert(receiving.getAddress() != "");
		assert(receiving.getTime() != null);
		
		this.receiving = receiving;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		assert(payment != null);
		assert(payment.getChange() != null);
		assert(payment.getChange() != "");
		assert(payment.getId() > 0);
		assert(payment.getPaymentType() != null);
		assert(payment.getPaymentType() != "");
		
		this.payment = payment;
	}

	public String getStatus(){
		return this.status;
	}

	public void setStatus(String s){
		assert(s != null);
		assert(s != "");
		
		this.status = s;
	}
}
