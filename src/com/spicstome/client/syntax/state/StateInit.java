package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.PronounDTO;

public class StateInit extends SyntaxState{

	public StateInit(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	@Override
	public  String check(WordDTO word,int range)
	{
		
		
		if(word instanceof ArticleDTO)
		{
			ArticleDTO article = (ArticleDTO)word;
			analyser.stateArticleSubject.setAcceptNext(article.getGender(), article.getNumber());
			analyser.currentState=analyser.stateArticleSubject;
		}
		else if(word instanceof NounDTO)
		{
			analyser.currentState=analyser.stateNounSubject;
			
		}
		else if(word instanceof PronounDTO)
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
