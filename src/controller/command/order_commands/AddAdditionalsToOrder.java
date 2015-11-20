package controller.command.order_commands;

import java.util.ArrayList;

import controller.command.Command;
import dao.OrderDAO;
import domain.Additional;
import domain.Order;

public class AddAdditionalsToOrder implements Command {
	
	private Order order;
	private OrderDAO orderDao;
	private String pageToRedirect;
	private String[] additionalIds;
	private int receiving; //1 = Delivery; 2 = Collect

	public AddAdditionalsToOrder() {
		super();
		this.orderDao = new OrderDAO();
	}
	
	public void setOrder(Order o){
		this.order = o;
	}
		
	public void setPageToRedirect(){
		if(receiving == 1){
			this.pageToRedirect = "/delivery.jsp";
		} else {
			this.pageToRedirect = "/collect.jsp";
		}
	}
	
	public void setAdditionalsIds(String[] ids){
		this.additionalIds = ids;
	}
	
	public void setAdditionalsToOrder(){
		try{
			this.getOrder().setAdditionals(new ArrayList<Additional>());
			for(String s : additionalIds){
					Additional d = this.orderDao.getAdditionalById(Integer.valueOf(s));
					this.order.getAdditionals().add(d);
				
			}
		}catch(NullPointerException npe){};
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setReceiving(int r){
		this.receiving = r;
	}
	
	public int getReceiving(){
		return this.receiving;
	}

	@Override
	public void execute() throws Exception {
		this.setPageToRedirect();
		this.setAdditionalsToOrder();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
