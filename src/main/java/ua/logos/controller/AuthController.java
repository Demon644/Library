package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.logos.domain.request.SigninRequest;
import ua.logos.domain.request.SignupRequest;
import ua.logos.domain.response.SigninResponse;
import ua.logos.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		authService.signup(signupRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
//	@PreAuthorize( "hasRole('ADMIN')")
	@PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
    	String token = authService.signin(signinRequest);
        return ResponseEntity.ok(new SigninResponse(token));
    }
}
