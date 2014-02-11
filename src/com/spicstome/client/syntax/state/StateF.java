package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.WordDTO;

/* if something is coming it's not valid anymore */

public class StateF extends SyntaxState{

	public StateF(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(WordDTO word,int range)
	{

		
			analyser.currentState=analyser.trashState;
			return null;
		

	}

}
