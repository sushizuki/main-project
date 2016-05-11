/** 
*    SaveOrder.java to define SaveOrder 
*    The purpose of this class is to save the client’s order
*/ 

package controller.command.order_commands;

import controller.command.Command;
import dao.OrderDAO;
import domain.Order;

public class SaveOrder implements Command {
	
	private OrderDAO orderDao;
	private Order orderOfSession; // Order object from the current session
	private String pageToRedirect;

	public SaveOrder() {
		this.orderDao = new OrderDAO();
		this.pageToRedirect="/index.jsp"; //Default page to redirect
	}
	
	public void setOrder(Order orderOfSession){
		this.orderOfSession = orderOfSession;
	}

	@Override
	public void execute() throws Exception {
		try{
			if(this.orderOfSession != null){
				this.orderDao.insert(this.orderOfSession);
			}else{
				//Do nothing
			}
		}catch(Exception executeException){
			executeException.printStackTrace();
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
