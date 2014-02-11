package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public abstract class WordDTO extends PecsDTO implements Serializable {
	
	private static final long serialVersionUID = -4644499772441676322L;
	
	private Set<LogDTO> logs;
	private int favorite;
	
	public WordDTO() {
		
	}
	
	public WordDTO(Long id) {
		super(id);
	}
	
	public WordDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<LogDTO> logs,int favorite) {
		super(id, name, order, folder, image);		
		this.logs = logs;
		this.favorite=favorite;
	}
	
	public Set<LogDTO> getLogs() {
		return logs;
	}

	public void setLogs(Set<LogDTO> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Word []";
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
}
