package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.WordDTO;


public class StateTrash extends SyntaxState{

	

	public StateTrash(SyntaxAnalyser analyser) {
		super(false, analyser);
	}

	@Override
	public String check(WordDTO word,int range) 
	{
		return null;
	}

}
