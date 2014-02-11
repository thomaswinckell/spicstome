package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.PronounDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.syntax.french.SyntaxFrenchManager;
import com.spicstome.client.ui.widget.ImageRecord;

public class SyntaxAnalyser {

	public SyntaxState currentState;
	public SyntaxFrenchManager syntaxFrenchManager;
	protected ArrayList<ImageRecord> arrayRecord;
	
	public StateInit stateInit;
	public StateArticleSubject stateArticleSubject;
	public StateNounSubject stateNounSubject;
	public StateAdjectiveSubject stateAdjectiveSubject;
	public StatePronounSubject statePronounSubject;
	public StateVerb stateVerb;
	public StateAdjectiveComplement stateAdjectiveComplement;
	public StateArticleComplement stateArticleComplement;
	public StateNounComplement stateNounComplement;
	public StateF statefinal;
	
	public StateTrash trashState;
	
	public SyntaxAnalyser()
	{
		syntaxFrenchManager= new SyntaxFrenchManager();
		
		stateInit = new StateInit(this);
		stateArticleSubject = new StateArticleSubject(this);
		stateNounSubject = new StateNounSubject(this);
		stateAdjectiveSubject = new StateAdjectiveSubject(this);
		statePronounSubject = new StatePronounSubject(this);
		stateVerb = new StateVerb(this);
		stateAdjectiveComplement= new StateAdjectiveComplement(this);
		stateNounComplement = new StateNounComplement(this);
		stateArticleComplement = new StateArticleComplement(this);
		statefinal = new StateF(this);
		
		trashState = new StateTrash(this);
		
		currentState = stateInit;
	}
	
	public void init(ArrayList<ImageRecord> arrayRecord)
	{
		currentState = stateInit;
		this.arrayRecord=arrayRecord;
		stateVerb.nbInfinitifVerb=0;
	}

	public WordDTO extractArticle(int i)
	{
		return (WordDTO)arrayRecord.get(i).getAttributeAsObject(ImageRecord.DATA);
	}
	
	public String check(int range)
	{
		return currentState.check(extractArticle(range), range);
	}
	
	public void updateText(int rang,String text)
	{
		arrayRecord.get(rang).setAttribute(ImageRecord.PICTURE_NAME, text);
	}
	
	public String getText(int rang)
	{
		return arrayRecord.get(rang).getAttribute(ImageRecord.PICTURE_NAME);
	}
	
	public void addSentencesMarkers()
	{
		String firstWord =getText(0) ;
		char firstChar = firstWord.charAt(0);
		String F = String.valueOf(firstChar);
		F = F.toUpperCase();
		String rest = firstWord.substring(1);
		updateText(0,F+rest);
		
		String lastWord = getText(arrayRecord.size()-1);
		updateText(arrayRecord.size()-1,lastWord+".");
	}
	
	public void analyse()
	{
		/* matching and conjugate */
		for(int i=0;i<arrayRecord.size();i++)
		{
			String modif = check(i);
			
			if(modif!=null)
			{
				updateText(i, modif);
			}
		}
		
		/* formatting subject */
		
		if(arrayRecord.size()>=2)
		{
			if((extractArticle(0) instanceof PronounDTO) && (extractArticle(1) instanceof VerbDTO))
			{
				PronounDTO pronoun = (PronounDTO) extractArticle(0);
				VerbDTO verb = (VerbDTO) extractArticle(1);
				
				updateText(0, syntaxFrenchManager.formatPronoun(pronoun.getName(), verb.getName()));
			}
		}
		
		if(arrayRecord.size()>=1)
		{
			addSentencesMarkers();
		}
	}
	
	
	
}
