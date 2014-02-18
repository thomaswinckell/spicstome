package com.spicstome.client.dto;

import java.io.Serializable;

public class NounDTO extends SubjectDTO implements Serializable {


	private int uncountable;
	
	private static final long serialVersionUID = -2498592085862584021L;
	
	public NounDTO()
	{
		
	}
	
	public NounDTO(long id)
	{
		super(id);
	}
	
	public NounDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image,int favorite,int gender,int number,int uncountable)
	{
		super(id, name, order, folder, image,favorite,gender,number);
		
		this.uncountable=uncountable;
	}
	
	
	@Override
	public String toString() {
		return "Noun []";
	}

	public int getUncountable() {
		return uncountable;
	}

	public void setUncountable(int uncountable) {
		this.uncountable = uncountable;
	}

}
