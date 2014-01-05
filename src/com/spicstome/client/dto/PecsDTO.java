package com.spicstome.client.dto;

import java.io.Serializable;

public abstract class PecsDTO implements Serializable {
	
	private static final long serialVersionUID = 739051315384634937L;
	
	private Long id;
	private String name;
	private int order;
	private FolderDTO folder;
	private ImageDTO image;
	
	public PecsDTO() {
		
	}

	public PecsDTO(Long id) {
		this.id = id;
	}
	
	public PecsDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image) {
		this.id = id;
		this.name = name;
		this.order = order;
		this.folder = folder;
		this.image = image;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public FolderDTO getFolder() {
		return folder;
	}

	public void setFolder(FolderDTO folder) {
		this.folder = folder;
	}

	@Override
	public String toString() {
		return "Pecs [id=" + id + ", name=" + name + ", order=" + order
				+ ", folder=" + folder + "]";
	}	
}
