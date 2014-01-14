package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;

public class Folder extends Pecs implements Serializable {
	
	private static final long serialVersionUID = 836063685281646643L;
	
	private Set<Pecs> content;

	public Folder() {
	}
	
	public Folder(Long id) {
		super(id);
	}
	
	public Folder(FolderDTO folderDTO,Folder parent) {
		super(folderDTO,parent);
		Set<PecsDTO> pecsDTOs = folderDTO.getContent();
		if (pecsDTOs != null) {
			Set<Pecs> pecs = new HashSet<Pecs>(pecsDTOs.size());
			for (PecsDTO pecsDTO : pecsDTOs) {
				if (pecsDTO instanceof FolderDTO)
					pecs.add(new Folder((FolderDTO) pecsDTO,this));
				else
					pecs.add(new Article((ArticleDTO) pecsDTO,this));
			}
			this.content = pecs;
		}
	}
	
	public Set<Pecs> getContent() {
		return content;
	}

	public void setContent(Set<Pecs> content) {
		this.content = content;
	}

	public void addContent(Pecs pecs) {
		if (content == null) {
		      content = new HashSet<Pecs>();
		}
		content.add(pecs);
	}

	public void removeContent(Pecs pecs) {
		if (content == null) {
		      return;
		}
		content.remove(pecs);
	}

	@Override
	public String toString() {
		return "Folder [content=" + content + "]";
	}
}
