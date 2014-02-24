package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ReferentDTO extends UserDTO implements Serializable {
	
	private static final long serialVersionUID = -9215342098567221018L;
	
	private ArrayList<StudentDTO> students;
	
	public ReferentDTO() {
	}
	
	public ReferentDTO(Long id) {
		super(id);
	}

	public ReferentDTO(Long id, Date subscriptionDate, String firstName, String name, String email, String login, String password, ImageDTO image, ArrayList<StudentDTO> students) {
		super(id, subscriptionDate, firstName, name, email, login, password, image);
		this.students = students;
	}
	
	public ArrayList<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<StudentDTO> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Referent [students=" + students + "]";
	}	
}
