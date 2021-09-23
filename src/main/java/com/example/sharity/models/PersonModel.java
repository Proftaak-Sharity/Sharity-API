
package com.example.sharity.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;


@MappedSuperclass
public class PersonModel extends BaseModel {

	@Column(name = "first_name", nullable = false)
	@NotEmpty
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotEmpty
	private String lastName;

	@Column(unique = true, nullable = false)
	private String email;

	@Column
	private String password;


	public PersonModel(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public PersonModel() {

	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
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
