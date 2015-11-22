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
	
	public void setOrder(Order o){
		this.order = o;
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
			System.err.println("ERROR CASTING COLLECTING DATE AND TIME: ");
			e.printStackTrace();
		}
		this.collectTime = c;
	}

	@Override
	public void execute() throws Exception {
		if(this.collectTime!=null && this.order!=null){
			this.order.setReceiving(new Collect(collectTime));
		} else {
			System.out.println("Order: "+this.collectTime);
			System.out.println("Order: "+this.order);
			throw new Exception("ERROR ASSIGNING COLLECTING DETAILS");
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
