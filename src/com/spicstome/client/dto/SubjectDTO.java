package com.spicstome.client.dto;

import java.io.Serializable;

public class SubjectDTO extends WordDTO implements Serializable {

	private static final long serialVersionUID = -5401723683221445029L;

	private int gender;
	private int number;
	
	public SubjectDTO()
	{
		
	}
	
	public SubjectDTO(long id)
	{
		super(id);
	}
	
	public SubjectDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image,int favorite,int gender,int number)
	{
		super(id, name, order, folder, image,favorite);
		
		this.gender=gender;
		this.number=number;
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
