package domain;

import java.sql.SQLException;
import java.util.List;

import dao.OrderDAO;

public class Payment {
	
	private int id;
	private String paymentType;
	private String change;
	
	public Payment(){
		
	}
	
	public Payment(int id, String paymentType, String change) throws SQLException{
		this.setId(id);
		this.setChange(change);
		this.setPaymentType(paymentType);//the id of payment type
	}

	public Payment(String p) throws SQLException {
		this.setPaymentType(p); //the id of payment (1 card, 2 money)
	}
	
	public Payment(String type, double total, double change) throws SQLException {
		this.setPaymentType(type);
		this.setChange(change, total);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChange() {
		return change;
	}

	public void setChange(double value, double total) {
		this.change = String.valueOf(value - total);
	}
	
	public void setChange(String change) {
		this.change = change;
	}
	

	public int getPaymentId(String payment) throws SQLException {
		OrderDAO dao = new OrderDAO();
		List<String> list = dao.getPaymentTypes();
		
		for (int i = 1; i <= list.size(); i++) {
		    if(payment.equalsIgnoreCase(this.getPaymentType())){
		    	return i;
		    }
		}
		return 0; //if not found 0 = none
	}

	public String getPaymentType() {
		return paymentType;
	}
	
	//Get a number, look into category list from database to assign Name proper to the number
	public void setPaymentType(String i) throws SQLException {
		OrderDAO dao = new OrderDAO();
		List<String> list = dao.getPaymentTypes();
		try {
			this.paymentType = list.get(Integer.parseInt(i)-1);
		}catch(RuntimeException e){
			System.out.println("Error assigning payment type");
		}
	}
		
	public void setPaymentType(int i) {
		this.paymentType = String.valueOf(i);
	}
}
