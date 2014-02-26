package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.Date;

import com.spicstome.client.dto.LogDTO;

public class Log implements Comparable<Log>,Serializable {

	private static final long serialVersionUID = 8544863337442780335L;
	
	private Long id;
	private Student student;
	private String emailRecipient;
	private Date date;
	private int messageLength;
	private int executionTime;
	private int actions;
	
	public Log() {
	}
	
	public Log(Long id) {
		this.id = id;
	}
	
	public Log(LogDTO logDTO,Student student) {
		
		id = logDTO.getId();
		

		this.student = student;
		emailRecipient = logDTO.getEmailRecipient();
		date = logDTO.getDate();
		executionTime=logDTO.getExecutionTime();
		actions=logDTO.getActions();
		messageLength=logDTO.getMessageLength();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
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
	
	
	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}
	
	public int getMessageLength() {
		return messageLength;
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
	
	@Override
	public int compareTo(Log that) {
		
		final int BEFORE = -1;
        final int AFTER = 1;
 
        if (that == null) {
            return BEFORE;
        }
        
        if(getDate().before(that.getDate()))
        {
        	return BEFORE;
        }
        else
        {
        	return AFTER;
        }
		
	}

	@Override
	public String toString() {
		return "Log [id=";
	}	
}
