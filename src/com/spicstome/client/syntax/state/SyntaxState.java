package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;


public abstract class SyntaxState {

	public boolean acceptance;
	protected SyntaxAnalyser analyser;

	
	public SyntaxState(boolean acceptance,SyntaxAnalyser analyser)
	{
		this.acceptance=acceptance;
		this.analyser=analyser;
	}
	
	
	
	public  abstract String check(ArticleDTO article,int range);
}
