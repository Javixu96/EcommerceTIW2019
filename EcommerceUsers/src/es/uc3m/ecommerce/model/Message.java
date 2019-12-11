package es.uc3m.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int isRead;

	private String message;

	@ManyToOne
	@JoinColumn(name="receiverId")
	private Appuser receiver;

	@ManyToOne
	@JoinColumn(name="senderId")
	private Appuser sender;

	public Message() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIsRead() {
		return this.isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Appuser getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Appuser receiver) {
		this.receiver = receiver;
	}

	public Appuser getSender() {
		return this.sender;
	}

	public void setSender(Appuser sender) {
		this.sender = sender;
	}

}