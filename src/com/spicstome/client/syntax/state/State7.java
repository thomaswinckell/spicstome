package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.VerbDTO;

/* Appreciation */

public class State7 extends SyntaxState{

	public State7(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

	
		if(article instanceof NounDTO)
		{
			
			analyser.currentState=analyser.state8;
			return null;

		}
		else if(article instanceof VerbDTO)
		{
			
			analyser.currentState=analyser.state8;
			return null;

		}
		else
		{
			analyser.currentState=analyser.trashState;
			return null;
		}
		
		
	}

}
