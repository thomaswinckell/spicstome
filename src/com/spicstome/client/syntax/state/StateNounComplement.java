package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.VerbDTO;


public class StateNounComplement extends SyntaxState{

	public StateNounComplement(SyntaxAnalyser analyser) {
		super(true, analyser);
	}

	@Override
	public String check(WordDTO word, int range) {
		
		
		
		NounDTO subject = (NounDTO) analyser.extractArticle(range-1);
		
		if(word instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) word;

			analyser.currentState=analyser.stateAdjectiveComplement;
			
			return analyser.syntaxFrenchManager.match(subject.getGender(),subject.getNumber(), 
					adjective.getMatching1(),adjective.getMatching2(),adjective.getMatching3(),adjective.getMatching4());
		}
		else if(word instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) word;

			analyser.stateVerb.setAcceptNext(verb.getNegation(), verb.getName());
			return conjugueVerb(subject,verb);
		}
		else
		{
			analyser.currentState=analyser.trashState;
			
			return null;
		}
		
	}
	
	
	

}
