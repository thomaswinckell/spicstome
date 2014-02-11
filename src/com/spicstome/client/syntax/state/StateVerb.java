package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;

/* Etat */

public class StateVerb extends SyntaxState{

	boolean acceptVerb = false;
	boolean acceptAdjective = false;
	int nbInfinitifVerb = 0;
	
	public StateVerb(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	public void setAcceptNext(int negation,String verbName)
	{
	
		String infinitif = analyser.syntaxFrenchManager.getVerb(negation, verbName);
		analyser.stateVerb.acceptAdjective=analyser.syntaxFrenchManager.canBeFollowedByAdjective(infinitif);
		analyser.stateVerb.acceptVerb=analyser.syntaxFrenchManager.canBeFollowedByVerb(infinitif);

	}
	
	@Override
	public  String check(WordDTO word,int range)
	{

		if(word instanceof ArticleDTO)
		{	
			ArticleDTO article = (ArticleDTO)word;
			analyser.stateArticleComplement.setAcceptNext(article.getGender(), article.getNumber());
			analyser.currentState=analyser.stateArticleComplement;
			return null;
		}
		else if(word instanceof NounDTO)
		{
			analyser.currentState=analyser.stateNounComplement;
			return null;
		}
		else if(acceptVerb && nbInfinitifVerb<2 && word instanceof VerbDTO)
		{
			nbInfinitifVerb++;
			VerbDTO verb = (VerbDTO)word;
			setAcceptNext(verb.getNegation(), verb.getName());
			return null;
		}
		else if(acceptAdjective && word instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) word;
			SubjectDTO subject = (SubjectDTO) analyser.extractWord(0);
			
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
