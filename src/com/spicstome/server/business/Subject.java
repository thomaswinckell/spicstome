package com.spicstome.server.business;

import java.io.Serializable;

import com.spicstome.client.dto.SubjectDTO;

public class Subject extends Word implements Serializable {

	private static final long serialVersionUID = -5191013846944671225L;

	private int gender;
	private int number;
	
	public Subject() {		
	}
	
	public Subject(Long id) {
		super(id);
	}
	
	public Subject(SubjectDTO subjectDTO,Folder parent)
	{
		super(subjectDTO,parent);
		
		gender = subjectDTO.getGender();
		number = subjectDTO.getNumber();
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Subject []";
	}

	
	
}
