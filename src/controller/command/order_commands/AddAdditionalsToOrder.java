/** 
*    AddAdditionalsToOrder.java to define AddAdditionalsToOrder 
*    The purpose of this class is add the additionals selected by the client
*    into the order
*/ 

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
	
	public void setOrder(Order order){
		assert order != null: "Invalid Order: null value cannot be accepted";

		this.order = order;
	}
		
	public void setPageToRedirect(){
		if(receiving == 1){
			this.pageToRedirect = "/delivery.jsp";
		} else {
			this.pageToRedirect = "/collect.jsp";
		}
	}
	
	public void setPayment(String type, double total, double change) throws SQLException{
		assert type != null: "Invalid Type: null value cannot be accepted";
		assert total != 0: "Invalid Total: null value cannot be accepted";
		assert total < 0: "Invalid Total: negative value cannot be accepted";
		assert change != 0: "Invalid Change: null value cannot be accepted";
		assert change < 0: "Invalid Change: negative value cannot be accepted";

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
		assert ids != null: "Invalid Ids: null value cannot be accepted";

		this.additionalIds = ids;
	}
	
	public void setAdditionalsToOrder(){
		try{
			this.getOrder().setAdditionals(new ArrayList<Additional>());
			for(String stringOfAdditionals : additionalIds){
					Additional additionals = this.orderDao.getAdditionalById(Integer.valueOf(stringOfAdditionals));
					this.order.getAdditionals().add(additionals);
				
			}
		}catch(Exception exception){};
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setReceiving(int receiving){
		assert receiving != 0: "Invalid Receiving: null value cannot be accepted";
		assert receiving < 0: "Invalid Receiving: negative value cannot be accepted";

		this.receiving = receiving;
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
