package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Date;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = 5615667762906524784L;

	private UserDTO sender;
	private String messageHTML;
	private Date receivedDate;
	
	public MailDTO() {
		this.sender = new UserDTO();
		this.messageHTML = null;
		this.receivedDate = new Date();
	}
	
	public MailDTO(UserDTO sender, String messageHTML, Date receivedDate) {
		this.sender = sender;
		this.messageHTML = messageHTML;
		this.receivedDate = receivedDate;
	}

	public UserDTO getSender() {
		return sender;
	}

	public void setSender(UserDTO sender) {
		this.sender = sender;
	}

	public String getMessageHTML() {
		return messageHTML;
	}

	public void setMessageHTML(String messageHTML) {
		this.messageHTML = messageHTML;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Override
	public String toString() {
		return "MailDTO [sender=" + sender + ", messageHTML=" + messageHTML
				+ ", receivedDate=" + receivedDate + "]";
	}
}
