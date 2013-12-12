package com.spicstome.client.shared;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {
	
	private Set<Student> students;
	
	public Teacher()
	{
		students = new HashSet<Student>();
	}

	public Teacher(Date subscriptionDate, String login, String password, Image image)
	{
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
		return "Teacher [students=" + students + "]";
	}

}
