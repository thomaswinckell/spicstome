package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.SubjectDTO;

/* Etat */

public class State6 extends SyntaxState{

	public State6(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

		if(article instanceof NounDTO)
		{
			
			analyser.currentState=analyser.state8;
			return null;

		}
		else if(article instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) article;
			SubjectDTO subject = (SubjectDTO) analyser.extractArticle(0);
			
			analyser.currentState=analyser.state8;
			
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
