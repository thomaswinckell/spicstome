package com.spicstome.server.business;

import java.io.Serializable;

import com.spicstome.client.dto.AlbumDTO;

public class Album implements Serializable {

	private static final long serialVersionUID = 621121084573804362L;
	
	private Long id;
	private Folder folder;
	
	public Album() {
		
	}
	
	public Album(Long id) {
		this.id = id;
	}

	public Album(AlbumDTO albumDTO) {
		id = albumDTO.getId();
		folder = (albumDTO.getFolder()==null?null:new Folder(albumDTO.getFolder(),null));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
