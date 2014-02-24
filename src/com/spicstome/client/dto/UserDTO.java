package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 3555772168779223497L;
	
	private Long id;
	private Date subscriptionDate;
	private String firstName;
	private String name;
	private String email;
	private String login;
	private String password;
	private ImageDTO image;
	
	public UserDTO() {		
	}
	
	public UserDTO(Long id) {
		this.id = id;
	}
	
	public UserDTO(Long id, Date subscriptionDate, String firstName, String name, 
			String email, String login, String password, ImageDTO image)	{
		this.id = id;
		this.subscriptionDate = subscriptionDate;
		this.firstName = firstName;
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", image=" + image + "]";
	}
}
