package controller.command.order_commands;

import controller.command.Command;
import dao.OrderDAO;
import domain.Order;

public class SaveOrder implements Command {
	
	private OrderDAO orderDao;
	private Order order;
	private String pageToRedirect;

	public SaveOrder(Order o) {
		this.order=o;
		this.orderDao = new OrderDAO();
		this.pageToRedirect="/confirmation";
	}
	
	public void setOrder(Order o){
		this.order = o;
	}

	@Override
	public void execute() throws Exception {
		try{
			if(this.order != null){
				this.orderDao.insert(this.order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
