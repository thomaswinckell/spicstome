package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.PronounDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;

public abstract class SyntaxState {

	public boolean acceptance;
	protected SyntaxAnalyser analyser;

	
	public SyntaxState(boolean acceptance,SyntaxAnalyser analyser)
	{
		this.acceptance=acceptance;
		this.analyser=analyser;
	}
	
	
	
	public String conjugueVerb(SubjectDTO subject,VerbDTO verb)
	{
		
		analyser.currentState=analyser.stateVerb;
		
		int subjectPerson;
		
		if(subject instanceof PronounDTO)
		{
			PronounDTO pronoun = (PronounDTO) subject;
			subjectPerson = pronoun.getPerson();
		}
		else
		{
			subjectPerson=2;
		}
		
		return analyser.syntaxFrenchManager.conjugate(subjectPerson,subject.getNumber(),
				verb.getName(),verb.getNegation(),verb.getGroup(),
				verb.getIrregular1(),verb.getIrregular2(),verb.getIrregular3(),
				verb.getIrregular4(),verb.getIrregular5(),verb.getIrregular6())	;
	}
	
	public  abstract String check(WordDTO word,int range);
}
