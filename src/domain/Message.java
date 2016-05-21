/**
*    Message.java to define Message
*    Message describes a message that can be sent from a Client to the Administrator
*/

package domain;

import java.util.Calendar;

public class Message {

	private int idMessage;
	private Client sender;
	private String message;
	private Calendar dateSent;


	public int getId() {
		return idMessage;
	}

	public void setId(int id) {
		assert id > 0 : "Message ID value inconsistent";
		
		this.idMessage = id;
	}

	public Client getSender() {
		return sender;
	}

	public void setSender(Client sender) {
		assert sender != null: "Invalid sender: null value cannot be accepted"; 
		
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		assert message != null: "Invalid Message: null value cannot be accepted";
		
		this.message = message;
	}

	public Calendar getDateSent() {
		return dateSent;
	}

	public void setDateSent(Calendar dateSent) {
		assert dateSent != null: "Invalid Calendar: null value cannot be accepted";
		
		this.dateSent = dateSent;
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> 0663ac4c74f5e72f842595501caffc198ab89dd8
