package com.spicstome.client.shared;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Referent extends User {
	
	private Set<Student> students;
	
	public Referent() {
		students = new HashSet<Student>();
	}

	public Referent(Date subscriptionDate, String login, String password, Image image) {
		super(subscriptionDate, login, password, image);
		students = new HashSet<Student>();
	}
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student)
	{
		students.add(student);
	}
	
	public void removeStudent(Student student)
	{
		students.remove(student);
	}

	@Override
	public String toString() {
		return "Referent [students=" + students + "]";
	}	
}
