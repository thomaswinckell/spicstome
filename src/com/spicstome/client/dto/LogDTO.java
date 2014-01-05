package com.spicstome.client.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

public class LogDTO implements Serializable {

	private static final long serialVersionUID = 8544863337442780335L;
	
	private Long id;
	private StudentDTO student;
	private String emailRecipient;
	private Date date;
	private Set<ArticleDTO> articles;
	
	public LogDTO() {
	}
	
	public LogDTO(Long id) {
		this.id = id;
	}
	
	public LogDTO(Long id, StudentDTO student, String emailRecipient, Date date, Set<ArticleDTO> articles) {
		this.id = id;
		this.student = student;
		this.emailRecipient = emailRecipient;
		this.date = date;
		this.articles = articles;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public String getEmailRecipient() {
		return emailRecipient;
	}

	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleDTO> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", articles=" + articles + "]";
	}	
}
