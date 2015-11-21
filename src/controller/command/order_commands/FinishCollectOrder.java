package controller.command.order_commands;

import java.util.Calendar;

import controller.command.Command;
import domain.Collect;
import domain.Order;

public class FinishCollectOrder implements Command {
	
	private Order order;
	private String pageToRedirect;
	private Calendar collectTime;

	public FinishCollectOrder() {
		super();
		this.pageToRedirect="confirmation.jsp";
	}
	
	public void setOrder(Order o){
		this.order = o;
	}

	@Override
	public void execute() throws Exception {
		try{
			this.order.setReceiving(new Collect(collectTime));
			Command save = new SaveOrder(this.order);
			save.execute();
		} catch(Exception e){e.printStackTrace();};
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
