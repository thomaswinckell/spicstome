package com.spicstome.client.shared;

public class Student extends User{
	
	private Album album;
	private History history;
	
	public Student(String filename,String name)
	{
		super(filename,name);
	}

	public History getHistorique() {
		return history;
	}

	public void setHistorique(History historique) {
		this.history = historique;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}
