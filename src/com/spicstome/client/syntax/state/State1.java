package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;


public class State1 extends SyntaxState{

	public State1(SyntaxAnalyser analyser) {
		super(true, analyser);
	}

	@Override
	public String check(ArticleDTO article, int range) {
		
		
		
		SubjectDTO subject = (SubjectDTO) analyser.extractArticle(0);
		
		if(article instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) article;

			analyser.currentState=analyser.state2;
			
			return analyser.syntaxFrenchManager.match(subject.getGender(),subject.getNumber(), 
					adjective.getMatching1(),adjective.getMatching2(),adjective.getMatching3(),adjective.getMatching4());
		}
		else if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) article;

			return conjugueVerb(subject,verb);
		}
		else
		{
			analyser.currentState=analyser.trashState;
			
			return null;
		}
		
	}
	
	
	

}
