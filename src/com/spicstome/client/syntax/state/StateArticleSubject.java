package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.NounDTO;


/* Etat */

public class StateArticleSubject extends SyntaxState{

	ArticleDTO article;
	
	public StateArticleSubject(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	public void setArticle(ArticleDTO article)
	{
		this.article = article;
		
	}
	
	@Override
	public  String check(WordDTO word,int range)
	{

		if(word instanceof NounDTO)
		{	
			NounDTO noun = (NounDTO) word;
			
			
			if(analyser.syntaxFrenchManager.goodArticle(article.getName(),
					article.getGender(), article.getNumber(),
					noun.getGender(), noun.getNumber(),noun.getUncountable()))
			{
				analyser.currentState=analyser.stateNounSubject;
				analyser.nbGoodWord++;
				return null;
			}
			else
			{
				analyser.currentState=analyser.trashState;
				return null;
			}
			
		}
		else
		{
			analyser.currentState=analyser.trashState;
			return null;
		}
		
		
	}

}
