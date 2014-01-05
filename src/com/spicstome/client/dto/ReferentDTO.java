package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ReferentDTO extends UserDTO implements Serializable {
	
	private static final long serialVersionUID = -9215342098567221018L;
	
	private Set<StudentDTO> students;
	
	public ReferentDTO() {
	}
	
	public ReferentDTO(Long id) {
		super(id);
	}

	public ReferentDTO(Long id, Date subscriptionDate, String firstName, String name, String email, String login, String password, ImageDTO image, Set<StudentDTO> students) {
		super(id, subscriptionDate, firstName, name, email, login, password, image);
		this.students = students;
	}
	
	public Set<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentDTO> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Referent [students=" + students + "]";
	}	
}
