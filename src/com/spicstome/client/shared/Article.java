package com.spicstome.client.shared;

public class Article extends Pecs{

	private Folder folder;
	
	public Article()
	{
		
	}
	
	public Article(String name,String filename)
	{
		super(name,filename);
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	
}
