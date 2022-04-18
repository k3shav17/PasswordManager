package com.password.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pass_man")
public class PasswordManager {

	@Id
	private String passId;
	@Indexed(unique = true)
	private String siteName;
	private String password;
	private String mailId;

	public PasswordManager(String siteName, String password, String mailId) {
		super();
		this.siteName = siteName;
		this.password = password;
		this.mailId = mailId;
	}

	public PasswordManager() {
		
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

}
