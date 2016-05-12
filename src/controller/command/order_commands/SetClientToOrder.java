
/** 
*    SetClientToOrder.java to define SetClientToOrder 
*    The purpose of this class is to associate the user logged to his order 
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
		this.pageToRedirect = "/shopping-cart.jsp"; //Default page to redirect
	}
	
	public void setClient(int idUser){
		this.client = (Client)userDao.getUserById(idUser);
	}	
	
	public void setClient(User user){
		this.client = (Client)user;
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public void execute() throws Exception {
		try{
			if(this.client!=null){
				this.order.setClient(this.client);
			} else {
				this.pageToRedirect = "login.jsp";
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
