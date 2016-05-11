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
		this.idMessage = id;
	}

	public Client getSender() {
		return sender;
	}

	public void setSender(Client sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Calendar getDateSent() {
		return dateSent;
	}

	public void setDateSent(Calendar dateSent) {
		this.dateSent = dateSent;
	}

}
