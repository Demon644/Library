package ua.logos.service;

import ua.logos.domain.UserDTO;

import java.util.List;

public interface UserService {

    void save(UserDTO dto);

    boolean existsByUsername(String username);

    UserDTO findByUsername(String username);

    String signin(String username, String password);

    UserDTO changeLogin(Long id);

    UserDTO changePassword(Long id);

    UserDTO changeUserType(Long id);

    UserDTO changeUserToken(Long id);

    UserDTO changeImage(Long id);

    UserDTO changeUserById(Long id);

    List<UserDTO> findAllUsers();

    void deleteUserById(Long id);

    void addImageToUser (Long id, String fileName);
}
