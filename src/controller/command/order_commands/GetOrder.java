/** 
*    GetOrder.java to define GetOrder 
*    {purpose} 
*/ 

package controller.command.order_commands;

import javax.servlet.http.HttpSession;

import controller.command.Command;
import domain.Order;

public class GetOrder implements Command {
	
	private Order order;
	private HttpSession session;
	private String pageToRedirec;

	public GetOrder() {
		this.pageToRedirec = "/shopping-cart.jsp"; //Default page to redirec
	}
	
	public void setSession(HttpSession s){
		this.session = s;
	}
	
	public void setPageToRedirect(String p){
		this.pageToRedirec = p;
	}
	
	public Order getOrder(){
		return this.order;
	}

	@Override
	public void execute() throws Exception {
		this.order = (Order) this.session.getAttribute("order");
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirec;
	}

}
