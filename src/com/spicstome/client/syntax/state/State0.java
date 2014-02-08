package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.PronounDTO;

public class State0 extends SyntaxState{

	public State0(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{
		
		
		if(article instanceof NounDTO)
		{
			analyser.currentState=analyser.state1;
			
		}
		else if(article instanceof PronounDTO)
		{
			analyser.currentState=analyser.state3;
			
		}
		else
		{
			analyser.currentState=analyser.trashState;
		}
		
		return null;
	}

}
