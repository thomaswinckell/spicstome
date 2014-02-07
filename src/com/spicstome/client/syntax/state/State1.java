package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageRecord;

public class State1 extends SyntaxState{

	public State1(SyntaxAnalyser analyser) {
		super(true, analyser);
	}

	@Override
	public void check(ImageRecord record, int range,
			ArrayList<ImageRecord> arrayRecord) {
		
		ArticleDTO article = extractArticle(record);
		ImageRecord recordVerb = arrayRecord.get(range);
		
		SubjectDTO subject = (SubjectDTO) extractArticle(arrayRecord.get(0));
		
		if(article instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjective = (AdjectiveDTO) article;
			analyser.match(subject, adjective, recordVerb);		
			analyser.currentState=analyser.state2;
		}
		else if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) article;
			analyser.conjugate(subject, verb, recordVerb);	
			analyser.currentState=analyser.state3;
		}
		else
		{
			analyser.currentState=analyser.trashState;
		}
		
	}
	
	
	

}
