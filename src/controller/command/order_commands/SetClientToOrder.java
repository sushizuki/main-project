/** 
*    SetClientToOrder.java to define SetClientToOrder 
*    {purpose} 
*/ 

package controller.command.order_commands;

import controller.command.Command;
import dao.UserDAO;
import domain.Client;
import domain.Order;
import domain.User;

public class SetClientToOrder implements Command{
	
	private Order order;
	private String pageToRedirect;
	private Client client;
	private UserDAO userDao;

	public SetClientToOrder() {
		this.userDao = new UserDAO();
		this.pageToRedirect = "/shopping-cart.jsp";
	}
	
	public void setClient(int id){
		this.client = (Client)userDao.getUserById(id);
	}	
	
	public void setClient(User u){
		this.client = (Client)u;
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public void execute() throws Exception {
		if(this.client!=null){
			this.order.setClient(this.client);
		} else {
			this.pageToRedirect = "login.jsp";
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
