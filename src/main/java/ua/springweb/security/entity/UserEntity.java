package ua.springweb.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import ua.springweb.security.enumeration.Role;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	private String email;
	private String password;

	@Column(name = "full_name")
	private String fullName;

	private String age;
	
	@Enumerated(EnumType.ORDINAL)
	private Role role;

	public UserEntity() {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
