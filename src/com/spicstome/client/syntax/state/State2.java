package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageRecord;

public class State2 extends SyntaxState{

	public State2(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  void check(ImageRecord record,int range,ArrayList<ImageRecord> arrayRecord)
	{
		ArticleDTO article = extractArticle(record);
		
		if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO) article;
			SubjectDTO subject = (SubjectDTO) extractArticle(arrayRecord.get(0));
			ImageRecord recordVerb = arrayRecord.get(range);
			analyser.conjugate(subject, verb, recordVerb);
			
			analyser.currentState=analyser.state3;
		}
		else
		{
			analyser.currentState=analyser.trashState;
		}
	}

}
