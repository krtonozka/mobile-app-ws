package com.krtonozka.springboot.ui.controller.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequest {
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull(message="Nastav si heslo noumo")
	@Size(min=8, max=16, message="Password has to be more than 8 and less than 16 characters")
	private String password;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
