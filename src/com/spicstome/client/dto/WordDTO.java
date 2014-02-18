package com.spicstome.client.dto;

import java.io.Serializable;

public abstract class WordDTO extends PecsDTO implements Serializable {
	
	private static final long serialVersionUID = -4644499772441676322L;
	
	private int favorite;
	
	public WordDTO() {
		
	}
	
	public WordDTO(Long id) {
		super(id);
	}
	
	public WordDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image,int favorite) {
		super(id, name, order, folder, image);		
		this.favorite=favorite;
	}
	

	@Override
	public String toString() {
		return "Word []";
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
}
