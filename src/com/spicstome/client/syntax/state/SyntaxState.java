package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
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
		String infinitif = analyser.syntaxFrenchManager.getVerb(verb.getNegation(), verb.getName());
		analyser.stateVerb.acceptAdjective=analyser.syntaxFrenchManager.canBeFollowedByAdjective(infinitif);
		analyser.stateVerb.acceptVerb=analyser.syntaxFrenchManager.canBeFollowedByVerb(infinitif);
		analyser.stateVerb.acceptNoun=true;
		
		analyser.currentState=analyser.stateVerb;
		
		int subjectPerson;
		
		if(subject instanceof NounDTO)
		{
			subjectPerson=2;
		}
		else
		{
			PronounDTO pronoun = (PronounDTO) subject;
			subjectPerson = pronoun.getPerson();
		}
		
		return analyser.syntaxFrenchManager.conjugate(subjectPerson,subject.getNumber(),
				verb.getName(),verb.getNegation(),verb.getGroup(),
				verb.getIrregular1(),verb.getIrregular2(),verb.getIrregular3(),
				verb.getIrregular4(),verb.getIrregular5(),verb.getIrregular6())	;
	}
	
	public  abstract String check(ArticleDTO article,int range);
}
