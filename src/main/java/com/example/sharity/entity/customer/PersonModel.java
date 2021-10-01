
package com.example.sharity.entity.customer;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@Getter
@Setter
@ToString
@MappedSuperclass
public class PersonModel {

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

	public PersonModel(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public PersonModel() {
	}
}
