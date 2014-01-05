package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;

public class Referent extends User implements Serializable {
	
	private static final long serialVersionUID = -9215342098567221018L;
	
	private Set<Student> students;
	
	public Referent() {
	}

	public Referent(Long id) {
		super(id);
	}
	
	public Referent(ReferentDTO referentDTO) {
		super(referentDTO);
		Set<StudentDTO> studentDTOs = referentDTO.getStudents();
		if (studentDTOs != null) {
			Set<Student> students = new HashSet<Student>(studentDTOs.size());
			for (StudentDTO studentDTO : studentDTOs) {
				students.add(new Student(studentDTO));
			}
			this.students = students;
		}
	}
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		if (students == null) {
			students = new HashSet<Student>();
		}
		students.add(student);
	}
	
	public void removeStudent(Student student) {
		if (students == null) {
			return;
		}
		students.remove(student);
	}

	@Override
	public String toString() {
		return "Referent [students=" + students + "]";
	}	
}
