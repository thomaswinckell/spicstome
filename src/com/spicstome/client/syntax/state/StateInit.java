package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.PronounDTO;

public class StateInit extends SyntaxState{

	public StateInit(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{
		
		
		if(article instanceof NounDTO)
		{
			analyser.currentState=analyser.stateNounSubject;
			
		}
		else if(article instanceof PronounDTO)
		{
			analyser.currentState=analyser.statePronounSubject;
			
		}
		else
		{
			analyser.currentState=analyser.trashState;
		}
		
		return null;
	}

}
