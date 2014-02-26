package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class StudentDTO extends UserDTO implements Serializable {
	
	private static final long serialVersionUID = 5196763171962166568L;
	
	private AlbumDTO album;
	private ArrayList<LogDTO> logs;
	
	public StudentDTO() {		
	}
	
	public StudentDTO(Long id) {	
		super(id);
	}
	
	public StudentDTO(Long id, Date subscriptionDate, String firstName, String name, String email, String login, String password, ImageDTO image, AlbumDTO album, 
			ArrayList<LogDTO> logs) {
		super(id, subscriptionDate, firstName, name, email, login, password, image);
		this.album = album;
		this.logs = logs;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public ArrayList<LogDTO> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<LogDTO> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Student [album=" + album + "]";
	}
}
