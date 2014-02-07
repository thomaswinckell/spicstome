package com.spicstome.client.syntax.state;

import java.util.ArrayList;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageRecord;

public class State0 extends SyntaxState{

	public State0(SyntaxAnalyser analyser) {
		super(false, analyser);
	}
	
	@Override
	public  void check(ImageRecord record,int range,ArrayList<ImageRecord> arrayRecord)
	{
		ArticleDTO article = extractArticle(record);
		
		if(article instanceof SubjectDTO)
		{
			analyser.currentState=analyser.state1;
		}
		else
		{
			analyser.currentState=analyser.trashState;
		}
	}

}
