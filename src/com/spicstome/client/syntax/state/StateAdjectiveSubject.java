package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;


public class StateAdjectiveSubject extends SyntaxState{

	public StateAdjectiveSubject(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(WordDTO word,int range)
	{
		
		if(word instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) word;
			SubjectDTO subject = (SubjectDTO) analyser.extractArticle(0);

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
