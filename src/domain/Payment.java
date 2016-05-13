/**
*    Payment.java to define Payment
*    Describes an Order's Payment properties.
*/ 

package domain;

import java.sql.SQLException;
import java.util.List;

import dao.OrderDAO;

public class Payment {

	private int idPayment;
	private String paymentType;
	private String changeOfPayment;

	public Payment(){

	}

	public Payment(int id, String paymentType, String change) throws SQLException{
		this.setId(id);
		this.setChange(change);
		this.setPaymentType(paymentType);//the id of payment type
	}

	public Payment(String payment) throws SQLException {
		this.setPaymentType(payment); //the id of payment (1 card, 2 money)
	}

	public Payment(String type, double total, double change) throws SQLException {
		this.setPaymentType(type);
		this.setChange(change, total);
	}

	public int getId() {
		return idPayment;
	}

	public void setId(int id) {
		this.idPayment = id;
	}

	public String getChange() {
		return changeOfPayment;
	}

	public void setChange(double value, double total) {
		this.changeOfPayment = String.valueOf(value - total);
	}

	public void setChange(String change) {
		this.changeOfPayment = change;
	}


	public int getPaymentId(String payment) throws SQLException {
		OrderDAO orderDao = new OrderDAO();
		List<String> listPayment = orderDao.getPaymentTypes();
		int returnValue=0;

		for (int i = 1; i <= listPayment.size(); i++) {
		    if(payment.equalsIgnoreCase(this.getPaymentType())){
		    	returnValue=i;
		    }
		}
		return returnValue; //if not found 0 = none
	}

	public String getPaymentType() {
		return paymentType;
	}

	//Get a number, look into category list from database to assign Name proper to the number
	public void setPaymentType(String i) throws SQLException {
		OrderDAO orderDao = new OrderDAO();
		List<String> listPayment = orderDao.getPaymentTypes();
		
		try {
			this.paymentType = listPayment.get(Integer.parseInt(i)-1);
		}catch(RuntimeException exception){
			System.out.println("Error assigning payment type");
		}
	}

	public void setPaymentType(int i) {
		this.paymentType = String.valueOf(i);
	}
}
