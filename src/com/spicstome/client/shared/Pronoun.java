package com.spicstome.client.shared;

import java.io.Serializable;
import com.spicstome.client.dto.PronounDTO;


public class Pronoun extends Subject implements Serializable {


	private static final long serialVersionUID = 5856815203348214365L;

	public Pronoun() {		
	}
	
	public Pronoun(Long id) {
		super(id);
	}
	
	public Pronoun(PronounDTO pronounDTO,Folder parent)
	{
		super(pronounDTO,parent);

	}
	
	@Override
	public String toString() {
		return "Pronoun []";
	}

	
}
