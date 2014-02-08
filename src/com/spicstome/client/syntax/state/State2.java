package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;


public class State2 extends SyntaxState{

	public State2(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{
		
		if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) article;
			SubjectDTO subject = (SubjectDTO) analyser.extractArticle(0);

			return conjugueVerb(subject,verb);
		}
		else
		{
			analyser.currentState=analyser.trashState;
			
			return null;
		}
	}

}
