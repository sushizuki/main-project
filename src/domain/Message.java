/**
*    Message.java to define Message
*    Message describes a message that can be sent from a Client to the Administrator
*/

package domain;

import java.util.Calendar;

public class Message {

	private int id;
	private Client sender;
	private String message;
	private Calendar dateSent;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		assert(id < 0);
		this.id = id;
	}

	public Client getSender() {
		return sender;
	}

	public void setSender(Client sender) {
		assert(sender != null);
		assert(sender.getAddress() != null);
		assert(sender.getAddress() != "");
		assert(sender.getEmail() != null);
		assert(sender.getEmail() != "");
		assert(sender.getId() > 0);
		assert(sender.getName() != null);
		assert(sender.getName() != "");
		assert(sender.getPhone() != null);
		assert(sender.getName() != "");
		
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		assert(message != null);
		assert(message != "");
		
		this.message = message;
	}

	public Calendar getDateSent() {
		return dateSent;
	}

	public void setDateSent(Calendar dateSent) {
		assert(dateSent != null);
		this.dateSent = dateSent;
	}

}
