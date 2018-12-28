package com.tec.annotation;

@Table("user")
public class Filter {
	@Columns("user_name")
	private String username;
	@Columns("password")
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}