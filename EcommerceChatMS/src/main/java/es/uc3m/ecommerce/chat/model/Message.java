package es.uc3m.ecommerce.chat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="senderid")
	private Appuser sender;

	@ManyToOne
	@JoinColumn(name="receiverid")
	private Appuser receiver;
	
	private String message;
	
	@Column(name="broadcast")
	private int broadcast;
	
	@Column(name="isread")
	private int isRead;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Appuser getSender() {
		return sender;
	}

	public void setSender(Appuser sender) {
		this.sender = sender;
	}

	public Appuser getReceiver() {
		return receiver;
	}

	public void setReceiver(Appuser receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(int broadcast) {
		this.broadcast = broadcast;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	
}
