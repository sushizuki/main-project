package controller.command.order_commands;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.command.Command;
import dao.OrderDAO;
import domain.Additional;
import domain.Order;
import domain.Payment;

public class AddAdditionalsToOrder implements Command {
	
	private Order order;
	private OrderDAO orderDao;
	private String pageToRedirect;
	private String[] additionalIds;
	private int receiving; 
	private Payment payment;

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
	
	public void setPayment(String type, double total, double change) throws SQLException{
		if(type.equalsIgnoreCase("money")){
			type = "2";
		} else if(type.equalsIgnoreCase("card")){
			type="1";
		}
		if(type=="2" && change>0){
			this.payment = new Payment(type, total, change);
		} else {
			this.payment = new Payment(type);
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
		if(this.payment!=null){
			this.order.setPayment(this.payment);
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}