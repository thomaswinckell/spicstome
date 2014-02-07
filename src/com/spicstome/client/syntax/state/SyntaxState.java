package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageRecord;

public abstract class SyntaxState {

	public boolean acceptance;
	protected SyntaxAnalyser analyser;
	
	public SyntaxState(boolean acceptance,SyntaxAnalyser analyser)
	{
		this.acceptance=acceptance;
		this.analyser=analyser;
	}
	
	public ArticleDTO extractArticle(ImageRecord record)
	{
		return (ArticleDTO)record.getAttributeAsObject(ImageRecord.DATA);
	}
	
	public  abstract void check(ImageRecord record,int range,ArrayList<ImageRecord> arrayRecord);
}
