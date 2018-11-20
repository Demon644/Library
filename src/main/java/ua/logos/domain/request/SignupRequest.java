package ua.logos.domain.request;

import lombok.Data;

@Data
public class SignupRequest {

	private String login;
	// private String email;
	private String password;
	private String passwordConfirmation;

}
