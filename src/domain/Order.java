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
	private List<Additional> additionalsList;
	private double totalPrice;
	private Receiving receiving; 
	private Payment paymentOfOrder;
	private String statusOfOrder;

	public Order(){	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert id > 0: "Invalid Order ID";
		
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		assert client != null: "Invalid Client: null value cannot be accepted";
		
		this.client = client;
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Product, Integer> items) {
		assert items != null: "Invalid Order items: null value cannot be accepted";
		
		this.items = items;
	}

	public List<Additional> getAdditionals() {
		return additionalsList;
	}

	public void setAdditionals(List<Additional> additionals) {
		assert additionals != null: "Invalid Additionals: null value cannot be accepted";

		this.additionalsList = additionals;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		assert totalPrice >= 0: "Invalid Total Price: negative values not accepted";
		
		this.totalPrice = totalPrice;
	}

	/**
	 * Calculates total price of order according to every item selected and quantity
	 */
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
		assert receiving != null: "Invalid Receiving: null value cannot be accepted";
		this.receiving = receiving;
	}

	public Payment getPayment() {
		return paymentOfOrder;
	}

	public void setPayment(Payment payment) {
		assert payment != null: "Invalid Payment: null value cannot be accepted";
		
		this.paymentOfOrder = payment;
	}

	public String getStatus(){
		return this.statusOfOrder;
	}

	public void setStatus(String status){		
		this.statusOfOrder = status;
	}
	
	/**
	 * Sets status of the order according to values that can be accepted
	 * @param status number from database
	 */
	public void setStatus(int status){
		if(status == NEW_ORDER_STATUS){
			this.statusOfOrder = "Novo Pedido";
		} else if(status == FINISHED_ORDER_STATUS){
			this.statusOfOrder = "Entregue";
		}
	}
}
