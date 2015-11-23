package controller.command.order_commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import controller.command.Command;
import domain.Address;
import domain.Delivery;
import domain.Order;

public class SetDeliveryDetails implements Command {
	
	private Order order;
	private Calendar collectTime;
	private String pageToRedirect;
	private Address deliveryAddress;

	public SetDeliveryDetails() {
		super();
		this.pageToRedirect="/confirmation.jsp";
	}
	
	public void setOrder(Order o){
		this.order = o;
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setAddress(Address a){
		this.deliveryAddress = a;
	}
	
	public void setAddress(String cep, String address, String complement){
		System.out.println("SETTING ADDRESS TO DELIVER");
		this.deliveryAddress = new Address(cep, address, complement);
	}
	
	public void setCollectTime(Calendar c){
		this.collectTime = c;
	}
	
	public void setCollectTime(String date, String time){
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c.setTime(df.parse(date+" "+time));
		} catch (ParseException e) {
			System.err.println("ERROR CASTING DELIVERY DATE AND TIME: ");
			e.printStackTrace();
		}
		this.collectTime = c;
	}

	@Override
	public void execute() throws Exception {
		if(this.collectTime!=null && this.order!=null && this.deliveryAddress!=null){
			this.order.setReceiving(new Delivery(this.deliveryAddress, this.collectTime));
			System.out.println("DELIVERY ADDRESS: "+this.order.getReceiving().getAddress().getAddress());
		} else {
			throw new Exception("ERROR ASSIGNING DELIVERY DETAILS");
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
