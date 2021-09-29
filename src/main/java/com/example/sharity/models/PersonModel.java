
package com.example.sharity.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class PersonModel {

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	@Column
	private String password;


}
