package com.spicstome.client.shared;

import java.util.HashSet;
import java.util.Set;

public class Folder extends Pecs {
	
	private Set<Pecs> content;

	public Folder() {
		content = new HashSet<Pecs>();
	}
	
	public Folder(String name, int order, Folder folder, Image image) {
		super(name,order,folder, image);
		content = new HashSet<Pecs>();
	}

	public Set<Pecs> getContent() {
		return content;
	}

	public void setContent(Set<Pecs> content) {
		this.content = content;
	}

	public void addContent(Pecs pecs)
	{
		content.add(pecs);
	}

	public void removeContent(Pecs pecs)
	{
		content.remove(pecs);
	}

	@Override
	public String toString() {
		return "Folder [content=" + content + "]";
	}
}
