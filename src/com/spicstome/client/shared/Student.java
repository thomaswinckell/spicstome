package com.spicstome.client.shared;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Student extends User {
	
	private Album album;
	private Set<Log> logs;
	private Set<Referent> referents;
	private Set<Teacher> teachers;
	
	public Student() {
		
	}
	
	public Student(Date subscriptionDate, String login, String password, Image image, Album album) {
		super(subscriptionDate, login, password, image);
		this.album = album;
		this.logs = new HashSet<Log>();
		this.referents = new HashSet<Referent>();
		this.teachers = new HashSet<Teacher>();
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public void addLog(Log log) {
		this.logs.add(log);
	}
	
	public void removeLog(Log log) {
		this.logs.remove(log);
	}
	
	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}
	
	public void removeTeacher(Teacher teacher) {
		this.teachers.remove(teacher);
	}
	
	public void addReferent(Referent referent) {
		this.referents.add(referent);
	}
	
	public void removeReferent(Referent referent) {
		this.referents.remove(referent);
	}
	
	public Set<Referent> getReferents() {
		return referents;
	}

	public void setReferents(Set<Referent> referents) {
		this.referents = referents;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Student [album=" + album + "]";
	}
}
