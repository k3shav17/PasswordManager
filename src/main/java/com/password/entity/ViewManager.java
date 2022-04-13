package com.password.entity;

import org.springframework.data.mongodb.core.index.Indexed;

public class ViewManager {
	
	@Indexed(unique = true)
	private String siteName;
	private String mailId;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
}
