package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.PronounDTO;
import com.spicstome.client.dto.VerbDTO;


public class StatePronounSubject extends SyntaxState{

	public StatePronounSubject(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

		if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) article;
			PronounDTO subject = (PronounDTO) analyser.extractArticle(0);
			
			return conjugueVerb(subject,verb);

		}
		else
		{
			analyser.currentState=analyser.trashState;
			return null;
		}
		
		
	}

}
