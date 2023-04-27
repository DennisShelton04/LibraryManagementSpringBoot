package com.librarymanagement.events;

public class UserEmailEvent {

	private String Emailid;
	private String Username;
	private String Password;

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	public String getEmailid() {
		return Emailid;
	}

	public UserEmailEvent(String emailid, String username, String password) {

		Emailid = emailid;
		Username = username;
		Password = password;
	}

}
