package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;

public class Student extends User implements Serializable {
	
	private static final long serialVersionUID = 5196763171962166568L;
	private Album album;
	private SortedSet<Log> logs;
	
	public Student() {		
	}
	
	public Student(Long id) {
		super(id);
	}
	
	public Student(StudentDTO studentDTO) {
		super(studentDTO);
		
		this.album = new Album(studentDTO.getAlbum());
		
		ArrayList<LogDTO> logDTOs = studentDTO.getLogs();
		if (logDTOs != null) {
			SortedSet<Log> logs = new TreeSet<Log>();
			for (LogDTO logDTO : logDTOs) {
				logs.add(new Log(logDTO,this));
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
			logs = new TreeSet<Log>();
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

	public void setLogs(SortedSet<Log> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Student [album=" + album + "]";
	}
}
