
package com.example.sharity.entity.customer;

import com.example.sharity.statics.PasswordGenerator;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.security.NoSuchAlgorithmException;

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

	public PersonModel(String firstName, String lastName, String email, String password) throws NoSuchAlgorithmException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = PasswordGenerator.getSHA512Password(password, PasswordGenerator.getSalt());
	}

	public PersonModel() {

	}
}
