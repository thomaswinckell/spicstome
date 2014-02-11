package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.SubjectDTO;

/* Ajdective's complement */

public class StateAdjectiveComplement extends SyntaxState{

	public StateAdjectiveComplement(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(WordDTO article,int range)
	{

		SubjectDTO subject = (SubjectDTO) analyser.extractWord(range-1);
	
		if(article instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) article;

			analyser.currentState=analyser.statefinal;
			
			return analyser.syntaxFrenchManager.match(subject.getGender(),subject.getNumber(), 
					adjective.getMatching1(),adjective.getMatching2(),adjective.getMatching3(),adjective.getMatching4());

		}
		else
		{
			analyser.currentState=analyser.trashState;
			return null;
		}
		
		
	}

}
