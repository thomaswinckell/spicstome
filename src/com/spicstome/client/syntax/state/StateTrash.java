package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.ArticleDTO;


public class StateTrash extends SyntaxState{

	

	public StateTrash(SyntaxAnalyser analyser) {
		super(false, analyser);
	}

	@Override
	public String check(ArticleDTO article,int range) 
	{
		return null;
	}

}
