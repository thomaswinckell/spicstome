package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;

public class State0 extends SyntaxState{

	public State0(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{
		
		
		if(article instanceof SubjectDTO)
		{
			analyser.currentState=analyser.state1;
			
		}
		else
		{
			analyser.currentState=analyser.trashState;
		}
		
		return null;
	}

}
