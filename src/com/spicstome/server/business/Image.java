package com.spicstome.server.business;

import java.io.Serializable;

import com.spicstome.client.dto.ImageDTO;

public class Image implements Serializable {
	
	private static final long serialVersionUID = 7007503420498291843L;
	private Long id;
	private String filename;
	
	public Image() {		
	}
	
	public Image(Long id) {
		this.id = id;
	}
	
	public Image(ImageDTO imageDTO) {
		id = imageDTO.getId();
		filename = imageDTO.getFilename();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", filename=" + filename + "]";
	}

}
