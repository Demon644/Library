package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.logos.entity.UserEntity;
import ua.logos.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(username);

        if(userEntity == null) {
            throw new UsernameNotFoundException("Could not find user with username [" + username + "]");
        }

        return User.builder()
                .username(userEntity.getLogin())
                .password(userEntity.getPassword())
                .authorities(userEntity.getUserType())
                .build();
    }
}
