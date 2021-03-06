
/** 
*    GetOrderList.java to define GetOrderList 
*    The purpose of this class is to return all orders saved in the database
*/ 

package controller.command.order_commands;

import java.util.List;

import javax.servlet.http.HttpSession;

import controller.command.Command;
import dao.OrderDAO;
import domain.Administrator;
import domain.Order;

public class GetOrderList implements Command {
	
	private List<Order> orderList;
	private OrderDAO orderDao;
	private HttpSession session;
	private String pageToRedirec;

	public GetOrderList() {
		this.orderDao = new OrderDAO();
		this.pageToRedirec = "/adm/order-list.jsp"; //Default page to redirect
	}
	
	public void setSession(HttpSession session){
		this.session = session;
	}
	
	public void setPageToRedirect(String page){
		this.pageToRedirec = page;
	}
	
	public List<Order> getOrderList(){
		return this.orderList;
	}

	@Override
	public void execute() throws Exception {
		if(session.getAttribute("user") instanceof Administrator ){
			this.orderList = orderDao.getList();
		}else{
			// Do nothing
		} 
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirec;
	}

}
