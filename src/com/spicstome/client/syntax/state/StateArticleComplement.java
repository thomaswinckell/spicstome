package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.WordDTO;


/* Ajdective's complement */

public class StateArticleComplement extends SyntaxState{

	int gender  = 0;
	int number = 0;
	
	public StateArticleComplement(SyntaxAnalyser analyser) {
		super(true, analyser);
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
			
			if((noun.getNumber()==number) && noun.getGender()==gender)
			{
				analyser.currentState=analyser.stateNounComplement;
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
