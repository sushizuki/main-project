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
	
	public void setOrder(Order order){
		assert order != null: "Invalid Order: null value cannot be accepted";

		this.order = order;
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setAddress(Address address){
		assert address != null: "Invalid Address: null value cannot be accepted";

		this.deliveryAddress = address;
	}
	
	public void setAddress(String cep, String address, String complement){
		assert cep != null: "Invalid CEP: null value cannot be accepted";
		assert address != null: "Invalid Address: null value cannot be accepted";
		assert complement != null: "Invalid Complement: null value cannot be accepted";

		this.deliveryAddress = new Address(cep, address, complement);
	}
	
	public void setCollectTime(Calendar dateTime){
		assert dateTime != null: "Invalid dateTime: null value cannot be accepted";

		this.collectTime = dateTime;
	}
	
	public void setCollectTime(String date, String time){
		assert date != null: "Invalid Date: null value cannot be accepted";
		assert time != null: "Invalid Time: null value cannot be accepted";
		
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
