
package com.example.sharity.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@MappedSuperclass
public class PersonModel {

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;


}
