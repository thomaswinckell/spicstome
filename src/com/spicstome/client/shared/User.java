package com.spicstome.client.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class User implements IsSerializable {
	
	private long id;
	private Date subscriptionDate;
	private String login;
	private String password;
	private Image image;
	
	public User() {
		
	}
	
	public User(Date subscriptionDate, String login, String password, Image image)	{
		this.subscriptionDate = subscriptionDate;
		this.login = login;
		this.password = password;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", image=" + image + "]";
	}
}
