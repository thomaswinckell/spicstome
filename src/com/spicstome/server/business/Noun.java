package com.spicstome.server.business;

import java.io.Serializable;
import com.spicstome.client.dto.NounDTO;


public class Noun extends Subject implements Serializable {

	
	private int uncountable;
	
	private static final long serialVersionUID = -7385294031853635611L;

	public Noun() {		
	}
	
	public Noun(Long id) {
		super(id);
	}
	
	public Noun(NounDTO nounDTO,Folder parent)
	{
		super(nounDTO,parent);
		this.uncountable = nounDTO.getUncountable();
	}
	
	public int getUncountable() {
		return uncountable;
	}

	public void setUncountable(int uncountable) {
		this.uncountable = uncountable;
	}
	
	@Override
	public String toString() {
		return "Noun []";
	}

	
}
