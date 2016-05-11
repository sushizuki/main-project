/** 
*    GetOrder.java to define GetOrder 
*    The purpose of this class is to return the order in the user session 
*/ 

package controller.command.order_commands;

import javax.servlet.http.HttpSession;

import controller.command.Command;
import domain.Order;

public class GetOrder implements Command {
	
	private Order orderOfTheUser;
	private HttpSession session;
	private String pageToRedirec; 

	public GetOrder() {
		this.pageToRedirec = "/shopping-cart.jsp"; //Default page to redirect
	}
	
	public void setSession(HttpSession s){
		this.session = s;
	}
	
	public void setPageToRedirect(String p){
		this.pageToRedirec = p;
	}
	
	public Order getOrder(){
		return this.orderOfTheUser;
	}

	@Override
	public void execute() throws Exception {
		this.orderOfTheUser = (Order) this.session.getAttribute("order");
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirec;
	}

}
