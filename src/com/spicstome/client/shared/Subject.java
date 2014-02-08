package com.spicstome.client.shared;

import java.io.Serializable;

import com.spicstome.client.dto.SubjectDTO;

public class Subject extends Article implements Serializable {

	private static final long serialVersionUID = -5191013846944671225L;

	private int gender;
	private int person;
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
		person = subjectDTO.getPerson();
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

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}
	
}
