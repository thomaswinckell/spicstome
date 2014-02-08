package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public class PronounDTO extends SubjectDTO implements Serializable {



	private static final long serialVersionUID = 93295496658731146L;


	public PronounDTO()
	{
		
	}
	
	public PronounDTO(long id)
	{
		super(id);
	}
	
	public PronounDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<LogDTO> logs,int favorite,int gender,int person,int number)
	{
		super(id, name, order, folder, image,logs,favorite,gender,person,number);
	}
	
	
	@Override
	public String toString() {
		return "Pronoun []";
	}

}
