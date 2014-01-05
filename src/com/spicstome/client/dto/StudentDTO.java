package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class StudentDTO extends UserDTO implements Serializable {
	
	private static final long serialVersionUID = 5196763171962166568L;
	
	private AlbumDTO album;
	private Set<LogDTO> logs;
	private Set<ReferentDTO> referents;
	private Set<TeacherDTO> teachers;
	
	public StudentDTO() {		
	}
	
	public StudentDTO(Long id) {	
		super(id);
	}
	
	public StudentDTO(Long id, Date subscriptionDate, String firstName, String name, String email, String login, String password, ImageDTO image, AlbumDTO album, 
			Set<LogDTO> logs, Set<ReferentDTO> referents, Set<TeacherDTO> teachers) {
		super(id, subscriptionDate, firstName, name, email, login, password, image);
		this.album = album;
		this.logs = logs;
		this.referents = referents;
		this.teachers = teachers;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}
	
	public Set<ReferentDTO> getReferents() {
		return referents;
	}

	public void setReferents(Set<ReferentDTO> referents) {
		this.referents = referents;
	}

	public Set<TeacherDTO> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<TeacherDTO> teachers) {
		this.teachers = teachers;
	}

	public Set<LogDTO> getLogs() {
		return logs;
	}

	public void setLogs(Set<LogDTO> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Student [album=" + album + "]";
	}
}
