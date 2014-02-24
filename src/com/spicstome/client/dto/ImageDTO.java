package com.spicstome.client.dto;

import java.io.Serializable;

public class ImageDTO implements Serializable {
	
	private static final long serialVersionUID = 7007503420498291843L;
	
	private Long id;
	private String filename;
	
	public ImageDTO() {		
	}
	
	public ImageDTO(Long id) {
		this.id = id;
	}
	
	public ImageDTO(Long id, String filename) {
		this.id = id;
		this.filename = filename;
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
