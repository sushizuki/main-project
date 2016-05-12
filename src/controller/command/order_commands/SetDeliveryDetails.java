/** 
*    SetDeliveryDetails.java to define SetDeliveryDetails 
*    The purpose of this class is to set the delivery details
*    when the client wants to receive the order by delivery
*/ 

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
	
	public void setAddress(Address address){
		this.deliveryAddress = address;
	}
	
	public void setAddress(String cep, String address, String complement){
		this.deliveryAddress = new Address(cep, address, complement);
	}
	
	public void setCollectTime(Calendar date){
		this.collectTime = date;
	}
	
	public void setCollectTime(String date, String time){
		Calendar dateTime = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			dateTime.setTime(df.parse(date+" "+time));
		} catch (ParseException exception) {
			System.err.println("ERROR CASTING DELIVERY DATE AND TIME: ");
			exception.printStackTrace();
		}
		this.collectTime = dateTime;
	}

	@Override
	public void execute() throws Exception {
		if(this.collectTime!=null && this.order!=null && this.deliveryAddress!=null){
			this.order.setReceiving(new Delivery(this.deliveryAddress, this.collectTime));
		} else {
			throw new Exception("ERROR ASSIGNING DELIVERY DETAILS");
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
