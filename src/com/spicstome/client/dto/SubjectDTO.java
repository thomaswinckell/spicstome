package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public class SubjectDTO extends ArticleDTO implements Serializable {

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
	
	public SubjectDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<LogDTO> logs,int favorite,int gender,int number)
	{
		super(id, name, order, folder, image,logs,favorite);
		
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
