package ua.springweb.security.domain;

import org.springframework.web.multipart.MultipartFile;

import ua.springweb.security.enumeration.Role;

public class RegisterRequest {

	
	private String email;
	
	private String password;
	
	private String passwordConfirmation;
	
	private Role role;
	
	//private MultipartFile file;
	
	public RegisterRequest() {}

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

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	
}
