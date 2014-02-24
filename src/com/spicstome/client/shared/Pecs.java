package com.spicstome.client.shared;

import java.io.Serializable;

import com.spicstome.client.dto.PecsDTO;

public abstract class Pecs implements Comparable<Pecs>,Serializable {
	
	private static final long serialVersionUID = 739051315384634937L;
	
	private Long id;
	private String name;
	private int order;
	private Folder folder;
	private Image image;
	
	public Pecs() {		
	}
	
	public Pecs(Long id) {
		this.id = id;
	}
	
	public Pecs(PecsDTO pecsDTO,Folder parent) {
		id = pecsDTO.getId();
		name = pecsDTO.getName();
		order = pecsDTO.getOrder();
		folder = parent;
		image = new Image(pecsDTO.getImage());
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
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
	
	@Override
	public int compareTo(Pecs that) {
		
		final int BEFORE = -1;
        final int AFTER = 1;
 
        if (that == null) {
            return BEFORE;
        }
        
        if(getOrder()<that.getOrder())
        {
        	return BEFORE;
        }
        else
        {
        	return AFTER;
        }
		
	}
}
