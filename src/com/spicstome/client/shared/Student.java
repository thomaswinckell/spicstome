package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;

public class Student extends User implements Serializable {
	
	private static final long serialVersionUID = 5196763171962166568L;
	private Album album;
	private Set<Log> logs;
	
	public Student() {		
	}
	
	public Student(Long id) {
		super(id);
	}
	
	public Student(StudentDTO studentDTO) {
		super(studentDTO);
		
		this.album = new Album(studentDTO.getAlbum());
		
		Set<LogDTO> logDTOs = studentDTO.getLogs();
		if (logDTOs != null) {
			Set<Log> logs = new HashSet<Log>(logDTOs.size());
			for (LogDTO logDTO : logDTOs) {
				logs.add(new Log(logDTO));
			}
			this.logs = logs;
		}		
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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
		return "Student [album=" + album + "]";
	}
}
