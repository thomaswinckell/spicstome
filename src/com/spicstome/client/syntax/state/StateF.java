package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;

/* if something is coming it's not valid anymore */

public class StateF extends SyntaxState{

	public StateF(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

		
			analyser.currentState=analyser.trashState;
			return null;
		

	}

}
