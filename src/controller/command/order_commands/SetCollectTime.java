/** 
*    SetCollectTime.java to define SetCollectTime 
*    The purpose is to set the collect time
*    when the client wants to collect the order at the administrator place
*/ 

package controller.command.order_commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import controller.command.Command;
import domain.Collect;
import domain.Order;

public class SetCollectTime implements Command {
	
	private Order order;
	private Calendar collectTime;
	private String pageToRedirect;

	public SetCollectTime() {
		super();
		this.pageToRedirect="/confirmation.jsp";
	}
	
	public void setOrder(Order order){
		this.order = order;
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setCollectTime(Calendar dateTime){
		this.collectTime = dateTime;
	}
	
	public void setCollectTime(String date, String time){
		Calendar dateTime = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			dateTime.setTime(dateFormat.parse(date+" "+time));
		} catch (ParseException e) {
			System.err.println("ERROR CASTING COLLECTING DATE AND TIME: ");
			e.printStackTrace();
		}
		this.collectTime = dateTime;
	}

	@Override
	public void execute() throws Exception {
		if(this.collectTime!=null && this.order!=null){
			this.order.setReceiving(new Collect(collectTime));
		} else {
			throw new Exception("ERROR ASSIGNING COLLECTING DETAILS");
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
