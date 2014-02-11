package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.LogDTO;

public abstract class Word extends Pecs implements Serializable {
	
	private static final long serialVersionUID = -4644499772441676322L;
	
	private Set<Log> logs;
	private int favorite;
	
	public Word() {		
	}
	
	public Word(Long id) {
		super(id);
	}

	public Word(WordDTO wordDTO,Folder parent) {
		super(wordDTO,parent);
		Set<LogDTO> logDTOs = wordDTO.getLogs();
		if (logDTOs != null) {
			Set<Log> logs = new HashSet<Log>(logDTOs.size());
			for (LogDTO logDTO : logDTOs) {
				logs.add(new Log(logDTO));
			}
			this.logs = logs;
		}
		this.favorite=wordDTO.getFavorite();
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
		return "Word []";
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

}
