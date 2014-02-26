package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Date;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = 5615667762906524784L;

	private long sender;
	private String messageHTML;
	private Date receivedDate;
	
	public MailDTO() {
		this.sender = -1;
		this.messageHTML = null;
		this.receivedDate = new Date();
	}
	
	public MailDTO(long sender, String messageHTML, Date receivedDate) {
		this.sender = sender;
		this.messageHTML = messageHTML;
		this.receivedDate = receivedDate;
	}

	public long getSender() {
		return sender;
	}

	public void setSender(long sender) {
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
