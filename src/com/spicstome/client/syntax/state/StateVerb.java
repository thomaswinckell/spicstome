package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;

/* Etat */

public class StateVerb extends SyntaxState{

	boolean acceptVerb = false;
	boolean acceptAdjective = false;
	boolean acceptNoun = false;
	
	public StateVerb(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

		if(acceptNoun && article instanceof NounDTO)
		{	
			analyser.currentState=analyser.stateAdjectiveComplement;
			return null;
		}
		else if(acceptVerb && article instanceof VerbDTO)
		{
			analyser.currentState=analyser.statefinal;
			return null;
		}
		else if(acceptAdjective && article instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) article;
			SubjectDTO subject = (SubjectDTO) analyser.extractArticle(0);
			
			analyser.currentState=analyser.statefinal;
			
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
