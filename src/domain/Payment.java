/**
*    Payment.java to define Payment
*    Describes an Order's Payment properties.
*/ 

package domain;

import java.sql.SQLException;
import java.util.List;

import dao.OrderDAO;
import exceptions.InvalidFormatException;
import exceptions.Validation;

public class Payment {

	private int idPayment;
	private String paymentType;
	private String changeOfPayment;

	public Payment(){
		//Nothing to do
	}

	public Payment(int id, String paymentType, String change) throws SQLException, InvalidFormatException{
		assert id > 0: "Invalid Payment ID";
		assert paymentType != null: "Invalid Payment type: null value cannot be accepted";
		
		this.setId(id);
		this.setChange(change);
		this.setPaymentType(paymentType);//the id of payment type
	}

	public Payment(String payment) throws SQLException {
		this.setPaymentType(payment); //the id of payment (1 card, 2 money)
	}

	public Payment(String type, double total, double change) throws SQLException {
		assert paymentType != null: "Invalid Payment type: null value cannot be accepted";
		assert total >= 0: "Invalid Payment: negative total cannot be accepted";
		
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

	public void setChange(String change) throws InvalidFormatException {
		if(Validation.isNumber(change)){
			this.changeOfPayment = change;
		}else{
			throw new InvalidFormatException("Troco deve conter apenas numeros!");
		}
	}


	public int getPaymentId(String payment) throws SQLException {
		assert payment != null: "Invalid Payment: null value cannot be accepted";
		
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
	public void setPaymentType(String paymentTypeNumber) throws SQLException {
		OrderDAO orderDao = new OrderDAO();
		List<String> listPayment = orderDao.getPaymentTypes();
		
		try {
			this.paymentType = listPayment.get(Integer.parseInt(paymentTypeNumber)-1);
		}catch(RuntimeException exception){
			System.out.println("Error assigning payment type");
		}
	}

	public void setPaymentType(int i) {
		this.paymentType = String.valueOf(i);
	}
}
