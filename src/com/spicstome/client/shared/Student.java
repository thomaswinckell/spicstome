package com.spicstome.client.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;

public class Student extends User implements Serializable {
	
	private static final long serialVersionUID = 5196763171962166568L;
	private Album album;
	private Set<Log> logs;
	private Set<Referent> referents;
	private Set<Teacher> teachers;
	
	public Student() {		
	}
	
	public Student(Long id) {
		super(id);
	}
	
	public Student(StudentDTO studentDTO) {
		super(studentDTO);
		
		this.album = new Album(studentDTO.getAlbum());
		
		Set<LogDTO> logDTOs = studentDTO.getLogs();
		if (logDTOs != null) {
			Set<Log> logs = new HashSet<Log>(logDTOs.size());
			for (LogDTO logDTO : logDTOs) {
				logs.add(new Log(logDTO));
			}
			this.logs = logs;
		}		
		Set<ReferentDTO> referentDTOs = studentDTO.getReferents();
		if (referentDTOs != null) {
			Set<Referent> referents = new HashSet<Referent>(referentDTOs.size());
			for (ReferentDTO referentDTO : referentDTOs) {
				referents.add(new Referent(referentDTO));
			}
			this.referents = referents;
		}
		Set<TeacherDTO> teacherDTOs = studentDTO.getTeachers();
		if (teacherDTOs != null) {
			Set<Teacher> teachers = new HashSet<Teacher>(teacherDTOs.size());
			for (TeacherDTO teacherDTO : teacherDTOs) {
				teachers.add(new Teacher(teacherDTO));
			}
			this.teachers = teachers;
		}
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public void addLog(Log log) {
		if (logs == null) {
			logs = new HashSet<Log>();
		}
		logs.add(log);
	}
	
	public void removeLog(Log log) {
		if (logs == null) {
			return;
		}
		logs.remove(log);
	}
	
	public void addTeacher(Teacher teacher) {
		if (teachers == null) {
			teachers = new HashSet<Teacher>();
		}
		teachers.add(teacher);
	}
	
	public void removeTeacher(Teacher teacher) {
		if (teachers == null) {
			return;
		}
		teachers.remove(teacher);
	}
	
	public void addReferent(Referent referent) {
		if (referents == null) {
			referents = new HashSet<Referent>();
		}
		referents.add(referent);
	}
	
	public void removeReferent(Referent referent) {
		if (referents == null) {
			return;
		}
		referents.remove(referent);
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
