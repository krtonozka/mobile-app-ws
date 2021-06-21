package com.krtonozka.springboot.ui.controller.model.request;

import javax.validation.constraints.NotNull;

public class UserDetailsUpdateRequest {
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	
	
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
}
