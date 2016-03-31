/** 
*    GetAvailableAdditionals.java to define GetAvailableAdditionals 
*    {purpose} 
*/ 

package controller.command.order_commands;

import java.util.ArrayList;
import java.util.List;

import controller.command.Command;
import dao.OrderDAO;
import domain.Additional;

public class GetAvailableAdditionals implements Command {
	
	private List<Additional> additionals;
	private OrderDAO orderDao;
	private String pageToRedirect;

	public GetAvailableAdditionals() {
		this.additionals = new ArrayList<Additional>();
		this.orderDao = new OrderDAO();
		this.pageToRedirect = "/shopping-cart.jsp";
	}
	
	public List<Additional> getAdditionals(){
		return this.additionals;
	}

	@Override
	public void execute() throws Exception {
		this.additionals = orderDao.getAdditionalsList();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
