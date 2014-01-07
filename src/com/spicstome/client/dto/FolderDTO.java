package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public class FolderDTO extends PecsDTO implements Serializable {
	
	private static final long serialVersionUID = 836063685281646643L;
	
	private Set<PecsDTO> content;

	public FolderDTO() {
	}
	
	public FolderDTO(Long id) {
		super(id);
	}
	
	public FolderDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<PecsDTO> content) {
		super(id, name, order, folder, image);		
		this.content = content;
	}

	public Set<PecsDTO> getContent() {
		return content;
	}

	public void setContent(Set<PecsDTO> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Folder ("+this.getName()+" "+this.getImage().toString()+") [content=" + content + "]";
	}
}
