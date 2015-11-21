package controller.command.order_commands;

import java.util.Calendar;

import controller.command.Command;
import domain.Address;
import domain.Delivery;
import domain.Order;

public class FinishDeliveryOrder implements Command {
	
	private Order order;
	private String pageToRedirect;
	private Address deliveryAddress;
	private Calendar deliveryTime;

	public FinishDeliveryOrder() {
		super();
		this.pageToRedirect = "confirmation.jsp";
	}
	
	public void setDeliveryAddress(Address a){
		this.deliveryAddress = a;
	}
	
	public void setDeliveryTime(Calendar c){
		this.deliveryTime = c;
	}

	@Override
	public void execute() throws Exception {
		try{
			this.order.setReceiving(new Delivery(this.deliveryAddress, this.deliveryTime));
			Command save = new SaveOrder(this.order);
			save.execute();
		} catch(Exception e){e.printStackTrace();};
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
