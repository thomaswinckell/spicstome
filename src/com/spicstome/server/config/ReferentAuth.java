package com.spicstome.server.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ReferentAuth")
public class ReferentAuth {

	private String username;
	private String password;
	
	
	public ReferentAuth() {
		super();
	}
	public ReferentAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@XmlElement(name="userame")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@XmlElement(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ReferentAuth [username=" + username + ", password=" + password
				+ "]";
	}
	
}
