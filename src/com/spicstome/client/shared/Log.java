package com.spicstome.client.shared;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Log {

	private long id;
	private User user;
	private String emailRecipient;
	private Date date;
	private Set<Article> articles;
	
	public Log() {
		articles = new HashSet<Article>();
	}
	
	public Log(User user, String emailRecipient, Date date) {
		this.user = user;
		this.emailRecipient = emailRecipient;
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article)
	{
		articles.add(article);
	}
	
	public void removeLog(Article article)
	{
		articles.remove(article);
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", articles=" + articles + "]";
	}	
}
