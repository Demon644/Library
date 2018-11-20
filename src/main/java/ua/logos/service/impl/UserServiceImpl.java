package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.logos.config.jwt.JWTTokenProvider;
import ua.logos.domain.UserDTO;
import ua.logos.entity.UserEntity;
import ua.logos.exception.AlreadyExistsException;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.repository.UserRepository;
import ua.logos.service.UserService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDTO changeLogin(Long id) {
        return null;
    }

    @Override
    public UserDTO changePassword(Long id) {
        return null;
    }

    @Override
    public UserDTO changeUserType(Long id) {
        return null;
    }

    @Override
    public UserDTO changeUserToken(Long id) {
        return null;
    }

    @Override
    public UserDTO changeImage(Long id) {
        return null;
    }

    @Override
    public void addImageToUser(Long id, String fileName) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record with id[" + id + "] not found")
        );
        user.setImage(fileName);
        userRepository.save(user);
    }
    @Override
    public UserDTO changeUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        List<UserDTO> dtos = userMapper.mapAll(entities, UserDTO.class);
        return dtos;
    }

    @Override
    public void deleteUserById(Long id) {
    UserEntity entity = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Record with id [" + id + "] not found")
    );
        if(entity != null) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userMapper.map(userRepository.findByLogin(username), UserDTO.class);
    }

}
