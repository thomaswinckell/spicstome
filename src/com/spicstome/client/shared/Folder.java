package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;

public class Folder extends Pecs implements Serializable {
	
	private static final long serialVersionUID = 836063685281646643L;
	
	private SortedSet<Pecs> content;

	public Folder() {
	}
	
	public Folder(Long id) {
		super(id);
	}
	
	public Folder(FolderDTO folderDTO,Folder parent) {
		super(folderDTO,parent);
		ArrayList<PecsDTO> pecsDTOs = folderDTO.getContent();
		if (pecsDTOs != null) {
			SortedSet<Pecs> pecs = new TreeSet<Pecs>();
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

	public void setContent(SortedSet<Pecs> content) {
		this.content = content;
	}

	public void addContent(Pecs pecs) {
		if (content == null) {
		      content = new TreeSet<Pecs>();
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
