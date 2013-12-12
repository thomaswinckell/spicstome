package com.spicstome.client.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Image implements IsSerializable{
	
	private long id;
	private String filename;
	
	public Image()
	{
		
	}
	
	public Image(String filename)
	{
		this.filename = filename;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
