package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.SubjectDTO;
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

	public WordDTO extractWord(int i)
	{
		return (WordDTO)arrayRecord.get(i).getAttributeAsObject(ImageRecord.DATA);
	}
	
	public String check(int range)
	{
		return currentState.check(extractWord(range), range);
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
		/* backward update */
		for(int i=0;i<arrayRecord.size();i++)
		{
			String modif = check(i);
			
			if(modif!=null)
			{
				updateText(i, modif);
			}
		}
		
		/*forward update */
		/* formatting subject */
		
		if(arrayRecord.size()>=2)
		{
			if((extractWord(0) instanceof PronounDTO) && (extractWord(1) instanceof VerbDTO))
			{
				PronounDTO pronoun = (PronounDTO) extractWord(0);
				VerbDTO verb = (VerbDTO) extractWord(1);

				updateText(0, syntaxFrenchManager.formatPronoun(pronoun.getName(),conjugueVerb(pronoun, verb)));
			}
		}
		
		for(int i=0;i<arrayRecord.size();i++)
		{
			WordDTO word = extractWord(i);
			if(word instanceof ArticleDTO)
			{
				if(i+1<arrayRecord.size() && extractWord(i+1) instanceof NounDTO)
				{
					ArticleDTO article = (ArticleDTO)word;
					NounDTO noun = (NounDTO) extractWord(i+1);
					
					if(syntaxFrenchManager.goodArticle(article.getName(),article.getGender(), article.getNumber(), noun.getGender(), noun.getNumber(),noun.getUncountable()))
						updateText(i,syntaxFrenchManager.formatArticle(article.getName(), noun.getName()));
				}
			}
		}
		
		if(arrayRecord.size()>=1)
		{
			addSentencesMarkers();
		}
	}
	
	public String conjugueVerb(SubjectDTO subject,VerbDTO verb)
	{

		int subjectPerson;
		
		if(subject instanceof PronounDTO)
		{
			PronounDTO pronoun = (PronounDTO) subject;
			subjectPerson = pronoun.getPerson();
		}
		else
		{
			subjectPerson=2;
		}
		
		return syntaxFrenchManager.conjugate(subjectPerson,subject.getNumber(),
				verb.getName(),verb.getNegation(),verb.getGroup(),
				verb.getIrregular1(),verb.getIrregular2(),verb.getIrregular3(),
				verb.getIrregular4(),verb.getIrregular5(),verb.getIrregular6())	;
	}
	
}
