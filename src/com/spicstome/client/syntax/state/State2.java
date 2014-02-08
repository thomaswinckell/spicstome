package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;


public class State2 extends SyntaxState{

	public State2(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{
		
		if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) article;
			SubjectDTO subject = (SubjectDTO) analyser.extractArticle(0);

			if(verb.getType()==0)
				analyser.currentState=analyser.state3;
			else if(verb.getType()==1)
				analyser.currentState=analyser.state3;
			else if(verb.getType()==2)
				analyser.currentState=analyser.state3;
			else if(verb.getType()==3)
				analyser.currentState=analyser.state3;
			
			return analyser.syntaxFrenchManager.conjugate(subject.getPerson(),subject.getNumber(),
						verb.getName(),verb.getNegation(),verb.getGroup(),
						verb.getIrregular1(),verb.getIrregular2(),verb.getIrregular3(),
						verb.getIrregular4(),verb.getIrregular5(),verb.getIrregular6())	;
		}
		else
		{
			analyser.currentState=analyser.trashState;
			
			return null;
		}
	}

}
