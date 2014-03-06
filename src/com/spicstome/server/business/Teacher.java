package com.spicstome.server.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;

public class Teacher extends User implements Serializable {
	
	private static final long serialVersionUID = -4260648157475947590L;
	
	private SortedSet<Student> students;
	
	public Teacher() {
	}

	public Teacher(Long id) {
		super(id);
	}
	
	public Teacher(TeacherDTO teacherDTO) {
		super(teacherDTO);
		ArrayList<StudentDTO> studentDTOs = teacherDTO.getStudents();
		if (studentDTOs != null) {
			SortedSet<Student> students = new TreeSet<Student>();
			for (StudentDTO studentDTO : studentDTOs) {
				students.add(new Student(studentDTO));
			}
			this.students = students;
		}
	}
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(SortedSet<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		if (students == null) {
			students = new TreeSet<Student>();
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
