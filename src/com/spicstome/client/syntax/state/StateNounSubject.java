package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.VerbDTO;


public class StateNounSubject extends SyntaxState{

	public StateNounSubject(SyntaxAnalyser analyser) {
		super(true, analyser);
	}

	@Override
	public String check(ArticleDTO article, int range) {
		
		
		
		NounDTO subject = (NounDTO) analyser.extractArticle(0);
		
		if(article instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) article;

			analyser.currentState=analyser.stateNounAdjectiveSubject;
			
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
