/** 
*    GetAvailableAdditionals.java to define GetAvailableAdditionals 
*    The purpose of this class is to get all additional 
*    registered into the database 
*/ 

package controller.command.order_commands;

import java.util.ArrayList;
import java.util.List;

import controller.command.Command;
import dao.OrderDAO;
import domain.Additional;

public class GetAvailableAdditionals implements Command {
	
	private List<Additional> additionalsList;
	private OrderDAO orderDao; // Object to handle the connection and get order related info from the database
	private String pageToRedirect;

	public GetAvailableAdditionals() {
		this.additionalsList = new ArrayList<Additional>();
		this.orderDao = new OrderDAO();
		this.pageToRedirect = "/shopping-cart.jsp"; //Default page to redirect
	}
	
	public List<Additional> getAdditionals(){
		return this.additionalsList;
	}

	@Override
	public void execute() throws Exception {
		this.additionalsList = orderDao.getAdditionalsList();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
