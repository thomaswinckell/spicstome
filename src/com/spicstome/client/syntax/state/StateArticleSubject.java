package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.NounDTO;


/* Etat */

public class StateArticleSubject extends SyntaxState{

	int gender  = 0;
	int number = 0;
	
	public StateArticleSubject(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	public void setAcceptNext(int gender,int number)
	{
	
		this.gender=gender;
		this.number=number;
	}
	
	@Override
	public  String check(WordDTO word,int range)
	{

		if(word instanceof NounDTO)
		{	
			NounDTO noun = (NounDTO) word;
			
			
			if(analyser.syntaxFrenchManager.goodArticle(gender, number, noun.getGender(), noun.getNumber()))
			{
				analyser.currentState=analyser.stateNounSubject;
				return null;
			}
			else
			{
				analyser.currentState=analyser.trashState;
				return null;
			}
			
		}
		else
		{
			analyser.currentState=analyser.trashState;
			return null;
		}
		
		
	}

}
