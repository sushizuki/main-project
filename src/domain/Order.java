/**
*    Order.java to define Order
*    Order describes a set of Products and other details of a Client's order. 
*/

package domain;
import java.util.HashMap;
import java.util.List;

public class Order {
	public static final int NEW_ORDER_STATUS = 1;
	public static final int FINISHED_ORDER_STATUS = 2;

	private int id;
	private Client client;
	private HashMap<Product, Integer> items;
	private List<Additional> additionals;
	private double totalPrice;
	private Receiving receiving; 
	private Payment payment;
	private String status;

	public Order(){	}

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

	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status){		
		this.status = status;
	}
	
	public void setStatus(int status){
		if(status == NEW_ORDER_STATUS){
			this.status = "Novo Pedido";
		} else if(status == FINISHED_ORDER_STATUS){
			this.status = "Entregue";
		}
	}
}
