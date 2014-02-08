package com.spicstome.client.shared;

import java.io.Serializable;
import com.spicstome.client.dto.NounDTO;


public class Noun extends Subject implements Serializable {


	private static final long serialVersionUID = -7385294031853635611L;

	public Noun() {		
	}
	
	public Noun(Long id) {
		super(id);
	}
	
	public Noun(NounDTO nounDTO,Folder parent)
	{
		super(nounDTO,parent);

	}
	
	@Override
	public String toString() {
		return "Noun []";
	}

	
}
