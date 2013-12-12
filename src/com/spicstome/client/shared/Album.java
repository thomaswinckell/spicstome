package com.spicstome.client.shared;

public class Album {

	private long id;
	private Folder folder;
	
	public Album() {
		
	}
	
	public Album(Folder folder) {
		this.folder = folder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	@Override
	public String toString() {
		return "Album [id=" + id + ", folder=" + folder + "]";
	}
}
