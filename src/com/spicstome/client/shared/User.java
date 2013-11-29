package com.spicstome.client.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	
	private int id_user;
	private String login;
	private String avatar;
	
	public User()
	{

	}
	
	public User(String name,String filename)
	{
		this.avatar=filename;
		this.login=name;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
