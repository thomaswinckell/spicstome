package com.spicstome.client.shared;

import java.util.ArrayList;

public class Folder extends Pecs{
	
	private ArrayList<Pecs> content;

	
	public Folder(String name,String filename)
	{
		super(name,filename);
		content = new ArrayList<Pecs>();
	}
	
	public void addContent(Pecs pecs)
	{
		content.add(pecs);
	}

	public ArrayList<Pecs> getContent() {
		return content;
	}

	public void setContent(ArrayList<Pecs> content) {
		this.content = content;
	}


}
