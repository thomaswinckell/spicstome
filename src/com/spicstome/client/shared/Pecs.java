package com.spicstome.client.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class Pecs implements IsSerializable{
	
	protected int id;
	protected String filename;
	protected String name;
	protected int order;
	
	public Pecs()
	{

	}
	
	public Pecs(String name,String filename)
	{

		this.filename=filename;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}


	
	

}
