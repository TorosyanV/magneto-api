package com.magneto.web.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class RegistrationRequest {

	private String name;

	@Email(message = "{RegistrationRequest.Email.NotValidEmail}")
	@NotEmpty(message = "{RegistrationRequest.Email.NotEmpty}")
	private String email;
	@NotEmpty
	private String password;

	public RegistrationRequest() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
