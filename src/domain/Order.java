package domain;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class Order {

	private int id;
	private Client client;
	private ArrayList<Product> items;
	private ArrayList<Additional> additionals;
	private double totalPrice;
	private String deliveryAddress;
	private Calendar deliveryTime;

	private void emitirConfirmacao() {

	}

	private void agendarRecebimento() {

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

	public ArrayList<Product> getItems() {
		return items;
	}

	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}

	public ArrayList<Additional> getAdditionals() {
		return additionals;
	}

	public void setAdditionals(ArrayList<Additional> additionals) {
		this.additionals = additionals;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Calendar getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Calendar deliveryTime) {
		this.deliveryTime = deliveryTime;
	}	
	
}
