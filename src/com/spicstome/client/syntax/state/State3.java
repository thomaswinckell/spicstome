package com.spicstome.client.syntax.state;

import java.util.ArrayList;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageRecord;

public class State3 extends SyntaxState{

	public State3(SyntaxAnalyser analyser) {
		super(true, analyser);
	}
	
	@Override
	public  void check(ImageRecord record,int range,ArrayList<ImageRecord> arrayRecord)
	{
		ArticleDTO article = extractArticle(record);
		
		
		analyser.currentState=analyser.trashState;
		
	}

}
