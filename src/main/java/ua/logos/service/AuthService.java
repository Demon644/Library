package ua.logos.service;

import ua.logos.domain.request.SigninRequest;
import ua.logos.domain.request.SignupRequest;

public interface AuthService {

    void signup(SignupRequest request);

    String signin(SigninRequest request);

}
