package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.LogDTO;

public class Article extends Pecs implements Serializable {
	
	private static final long serialVersionUID = -4644499772441676322L;
	
	private Set<Log> logs;
	
	public Article() {		
	}
	
	public Article(Long id) {
		super(id);
	}

	public Article(ArticleDTO articleDTO) {
		super(articleDTO);
		Set<LogDTO> logDTOs = articleDTO.getLogs();
		if (logDTOs != null) {
			Set<Log> logs = new HashSet<Log>(logDTOs.size());
			for (LogDTO logDTO : logDTOs) {
				logs.add(new Log(logDTO));
			}
			this.logs = logs;
		}
	}
	
	public void addLog(Log log) {
		if (logs == null) {
		      logs = new HashSet<Log>();
		}
		logs.add(log);
	}

	public void removeLog(Log log) {
		if (logs == null) {
			return;
		}
		logs.remove(log);
	}
	
	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Article []";
	}
}
