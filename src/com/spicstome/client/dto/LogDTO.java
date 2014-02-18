package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Date;

public class LogDTO implements Serializable {

	private static final long serialVersionUID = 8544863337442780335L;
	
	private Long id;
	private StudentDTO student;
	private String emailRecipient;
	private Date date;
	private int messageLength;
	private int executionTime;
	private int actions;
	
	public LogDTO() {
	}
	
	public LogDTO(Long id) {
		this.id = id;
	}
	
	public LogDTO(Long id, StudentDTO student, String emailRecipient, Date date,int messageLength,int executionTime,int actions) {
		this.id = id;
		this.student = student;
		this.emailRecipient = emailRecipient;
		this.date = date;
		this.executionTime=executionTime;
		this.actions=actions;
		this.messageLength=messageLength;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public String getEmailRecipient() {
		return emailRecipient;
	}

	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


	@Override
	public String toString() {
		return "Log ";
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}	
}
