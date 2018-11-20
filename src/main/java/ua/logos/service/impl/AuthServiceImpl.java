package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.logos.config.jwt.JWTTokenProvider;
import ua.logos.domain.request.SigninRequest;
import ua.logos.domain.request.SignupRequest;
import ua.logos.entity.RoleEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exception.AlreadyExistsException;
import ua.logos.exception.ServerException;
import ua.logos.repository.UserRepository;
import ua.logos.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequest request) {

        if (userRepository.existsByLogin(request.getLogin())) {
            throw new AlreadyExistsException("User with username [" + request.getLogin() + "] already exists");
        }


            String password = request.getPassword();

            UserEntity user = new UserEntity();
            user.setLogin(request.getLogin());
            user.setPassword(passwordEncoder.encode(password));

            Set<RoleEntity> roles = new HashSet<>();
            roles.add(new RoleEntity("USER"));
            user.setRoles(roles);

            userRepository.save(user);

    }

    @Override
    public String signin(SigninRequest request) {
        final Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getLogin(),
                                request.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return token;
    }

}
