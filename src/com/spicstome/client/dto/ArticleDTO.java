package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public class ArticleDTO extends PecsDTO implements Serializable {
	
	private static final long serialVersionUID = -4644499772441676322L;
	
	private Set<LogDTO> logs;
	
	public ArticleDTO() {
		
	}
	
	public ArticleDTO(Long id) {
		super(id);
	}
	
	public ArticleDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<LogDTO> logs) {
		super(id, name, order, folder, image);		
		this.logs = logs;
	}
	
	public Set<LogDTO> getLogs() {
		return logs;
	}

	public void setLogs(Set<LogDTO> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Article []";
	}
}
