package com.spicstome.client.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class Pecs implements IsSerializable{
	
	private long id;
	private String name;
	private int order;
	private Folder folder;
	private Image image;
	
	public Pecs() {
		
	}
	
	public Pecs(String name, int order, Folder folder, Image image) {
		this.name = name;
		this.order = order;
		this.folder = folder;
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Override
	public String toString() {
		return "Pecs [id=" + id + ", name=" + name + ", order=" + order
				+ ", folder=" + folder + "]";
	}	
}
