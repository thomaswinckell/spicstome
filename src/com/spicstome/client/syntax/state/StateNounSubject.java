package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.VerbDTO;


public class StateNounSubject extends SyntaxState{

	public StateNounSubject(SyntaxAnalyser analyser) {
		super(true, analyser);
	}

	@Override
	public String check(WordDTO word, int range) {
		
		
		
		SubjectDTO subject = (SubjectDTO) analyser.extractWord(0);
		
		
		
		if(word instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) word;

			analyser.currentState=analyser.stateAdjectiveSubject;
			analyser.nbGoodWord++;
			
			return analyser.syntaxFrenchManager.match(subject.getGender(),subject.getNumber(), 
					adjective.getMatching1(),adjective.getMatching2(),adjective.getMatching3(),adjective.getMatching4());
		}
		else if(word instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) word;

			analyser.stateVerb.setAcceptNext(verb.getNegation(), verb.getName());
			analyser.nbGoodWord++;
			
			return conjugueVerb(subject,verb);
		}
		else
		{
			analyser.currentState=analyser.trashState;
			
			return null;
		}
		
	}
	
	
	

}
