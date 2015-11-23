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
		this.pageToRedirec = "/adm/order-list.jsp"; //Default page to redirec
	}
	
	public void setSession(HttpSession s){
		this.session = s;
	}
	
	public void setPageToRedirect(String p){
		this.pageToRedirec = p;
	}
	
	public List<Order> getOrderList(){
		return this.orderList;
	}

	@Override
	public void execute() throws Exception {
		if(session.getAttribute("user") instanceof Administrator ){
			this.orderList = orderDao.getList();
		} 
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirec;
	}

}
