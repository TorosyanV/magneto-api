package com.magneto.web.viewmodel;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationRequest {

	private String name;
	@Email
	private String email;
	@NotEmpty
	private String password;

	public String getName() {
		return name;
	}
	/// com

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
