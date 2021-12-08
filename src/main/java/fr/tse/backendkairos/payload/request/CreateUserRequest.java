package fr.tse.backendkairos.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateUserRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;

	private String managerId;

	@NotBlank
	private String role;
}
