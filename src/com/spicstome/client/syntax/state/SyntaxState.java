package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
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
		if(verb.getType()==0)
			analyser.currentState=analyser.state4;
		else if(verb.getType()==1)
			analyser.currentState=analyser.state5;
		else if(verb.getType()==2)
			analyser.currentState=analyser.state6;
		else if(verb.getType()==3)
			analyser.currentState=analyser.state7;
		
		return analyser.syntaxFrenchManager.conjugate(subject.getPerson(),subject.getNumber(),
				verb.getName(),verb.getNegation(),verb.getGroup(),
				verb.getIrregular1(),verb.getIrregular2(),verb.getIrregular3(),
				verb.getIrregular4(),verb.getIrregular5(),verb.getIrregular6())	;
	}
	
	public  abstract String check(ArticleDTO article,int range);
}
