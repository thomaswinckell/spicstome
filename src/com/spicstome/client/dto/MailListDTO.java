package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class MailListDTO implements Serializable {

	private static final long serialVersionUID = -7238733754310888365L;
	
	private ArrayList<MailDTO> mails;
	private int startPosition, nextStartPosition;
	private boolean hasNext, hasPrevious;
	
	public MailListDTO() {
		super();
		this.mails = new ArrayList<MailDTO>();
		this.startPosition = -1;
		this.nextStartPosition = 0;
		hasNext = false;
		hasPrevious = false;
	}
	
	public MailListDTO(ArrayList<MailDTO> mails, int startPosition, int nextStartPosition, boolean hasNext, boolean hasPrevious) {
		super();
		this.mails = mails;
		this.startPosition = startPosition;
		this.nextStartPosition = nextStartPosition;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
	}

	public ArrayList<MailDTO> getMails() {
		return mails;
	}

	public void setMails(ArrayList<MailDTO> mails) {
		this.mails = mails;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getNextStartPosition() {
		return nextStartPosition;
	}

	public void setNextStartPosition(int nextStartPosition) {
		this.nextStartPosition = nextStartPosition;
	}

	public boolean hasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean hasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	@Override
	public String toString() {
		return "MailListDTO [mails=" + mails + ", startPosition="
				+ startPosition + ", nextStartPosition="
				+ nextStartPosition + ", hasNext=" + hasNext + ", hasPrevious="
				+ hasPrevious + "]";
	}
}
