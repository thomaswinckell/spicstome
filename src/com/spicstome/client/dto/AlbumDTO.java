package com.spicstome.client.dto;

import java.io.Serializable;

public class AlbumDTO implements Serializable {

	private static final long serialVersionUID = 621121084573804362L;
	
	private Long id;
	private FolderDTO folder;
	
	public AlbumDTO() {		
	}
	
	public AlbumDTO(Long id) {
		this.id = id;
	}
	
	public AlbumDTO(Long id, FolderDTO folder) {
		this.id = id;
		this.folder = folder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FolderDTO getFolder() {
		return folder;
	}

	public void setFolder(FolderDTO folder) {
		this.folder = folder;
	}
	
	@Override
	public String toString() {
		return "Album [id=" + id + ", folder=" + folder + "]";
	}
}
