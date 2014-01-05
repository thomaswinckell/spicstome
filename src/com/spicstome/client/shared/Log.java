package com.spicstome.client.shared;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.LogDTO;

public class Log implements Serializable {

	private static final long serialVersionUID = 8544863337442780335L;
	
	private Long id;
	private Student student;
	private String emailRecipient;
	private Date date;
	private Set<Article> articles;
	
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
		
		Set<ArticleDTO> articleDTOs = logDTO.getArticles();
		if (articleDTOs != null) {
			Set<Article> articles = new HashSet<Article>(articleDTOs.size());
			for (ArticleDTO articleDTO : articleDTOs) {
				articles.add(new Article(articleDTO));
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
	
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article) {
		if (articles == null) {
		      articles = new HashSet<Article>();
		}
		articles.add(article);
	}
	
	public void removeLog(Article article) {
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
