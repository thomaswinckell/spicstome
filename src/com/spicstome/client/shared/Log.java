package com.spicstome.client.shared;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.SubjectDTO;

public class Log implements Serializable {

	private static final long serialVersionUID = 8544863337442780335L;
	
	private Long id;
	private Student student;
	private String emailRecipient;
	private Date date;
	private Set<Word> articles;
	
	public Log() {
	}
	
	public Log(Long id) {
		this.id = id;
	}
	
	public Log(LogDTO logDTO) {
		
		id = logDTO.getId();
		student = new Student(logDTO.getStudent());
		emailRecipient = logDTO.getEmailRecipient();
		date = logDTO.getDate();
		
		Set<WordDTO> articleDTOs = logDTO.getArticles();
		if (articleDTOs != null) {
			Set<Word> articles = new HashSet<Word>(articleDTOs.size());
			for (WordDTO articleDTO : articleDTOs) {
				if(articleDTO instanceof SubjectDTO)
					articles.add(new Subject((SubjectDTO)articleDTO,null));
			}
			this.articles = articles;
		}
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
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
	
	public Set<Word> getArticles() {
		return articles;
	}

	public void setArticles(Set<Word> articles) {
		this.articles = articles;
	}

	public void addArticle(Word article) {
		if (articles == null) {
		      articles = new HashSet<Word>();
		}
		articles.add(article);
	}
	
	public void removeLog(Word article) {
		if (articles == null) {
		      return;
		}
		articles.remove(article);
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", articles=" + articles + "]";
	}	
}
