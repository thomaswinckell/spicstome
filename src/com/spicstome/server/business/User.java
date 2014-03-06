package com.spicstome.server.business;

import java.io.Serializable;
import java.util.Date;

import com.spicstome.client.dto.UserDTO;

public abstract class User implements Comparable<User>,Serializable {
	
	private static final long serialVersionUID = 3555772168779223497L;
	
	private Long id;
	private Date subscriptionDate;
	private String firstName;
	private String name;
	private String email;
	private String login;
	private String password;
	private Image image;
	
	public User() {		
	}
	
	public User(Long id)	{
		this.id = id;
	}
	
	public User(UserDTO userDTO) {
		id = userDTO.getId();
		subscriptionDate = userDTO.getSubscriptionDate();
		firstName = userDTO.getFirstName();
		name = userDTO.getName();
		email = userDTO.getEmail();
		login = userDTO.getLogin();
		password = userDTO.getPassword();
		image = new Image(userDTO.getImage());
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
	
	@Override
	public int compareTo(User that) {
		
		final int BEFORE = -1;
        final int AFTER = 1;
 
        if (that == null || getFirstName().length()==0 || that.getFirstName().length()==0) {
            return BEFORE;
        }
        
        
        
        if(getFirstName().charAt(0)<that.getFirstName().charAt(0))
        {
        	return BEFORE;
        }
        else
        {
        	return AFTER;
        }
		
	}
}
