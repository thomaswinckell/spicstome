package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;


public class State5 extends SyntaxState{

	public State5(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

		
		
		analyser.currentState=analyser.trashState;
		
		return null;
		
	}

}
