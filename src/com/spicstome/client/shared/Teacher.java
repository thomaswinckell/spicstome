package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;

public class Teacher extends User implements Serializable {
	
	private static final long serialVersionUID = -4260648157475947590L;
	
	private Set<Student> students;
	
	public Teacher() {
	}

	public Teacher(Long id) {
		super(id);
	}
	
	public Teacher(TeacherDTO teacherDTO) {
		super(teacherDTO);
		Set<StudentDTO> studentDTOs = teacherDTO.getStudents();
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
		return "Teacher [students=" + students + "]";
	}

}
