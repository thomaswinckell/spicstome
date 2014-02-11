package com.spicstome.client.shared;

import java.io.Serializable;
import com.spicstome.client.dto.ArticleDTO;



public class Article extends Subject implements Serializable {


	private static final long serialVersionUID = 6959653613119827505L;

	public Article() {		
	}
	
	public Article(Long id) {
		super(id);
	}
	
	public Article(ArticleDTO articleDTO,Folder parent)
	{
		super(articleDTO,parent);

	}
	
	@Override
	public String toString() {
		return "Article []";
	}

	
}
