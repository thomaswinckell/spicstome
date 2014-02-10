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
	int nbInfinitifVerb = 0;
	
	public StateVerb(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	public void setAcceptNext(int negation,String verbName)
	{
	
		String infinitif = analyser.syntaxFrenchManager.getVerb(negation, verbName);
		analyser.stateVerb.acceptAdjective=analyser.syntaxFrenchManager.canBeFollowedByAdjective(infinitif);
		analyser.stateVerb.acceptVerb=analyser.syntaxFrenchManager.canBeFollowedByVerb(infinitif);
		analyser.stateVerb.acceptNoun=true;
	}
	
	@Override
	public  String check(ArticleDTO article,int range)
	{

		if(acceptNoun && article instanceof NounDTO)
		{	
			analyser.currentState=analyser.stateAdjectiveComplement;
			return null;
		}
		
	
		else if(acceptVerb && nbInfinitifVerb<2 && article instanceof VerbDTO)
		{
			nbInfinitifVerb++;
			VerbDTO verb = (VerbDTO)article;
			setAcceptNext(verb.getNegation(), verb.getName());
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
