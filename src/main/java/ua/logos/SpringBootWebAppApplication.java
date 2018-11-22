package ua.logos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.logos.entity.RoleEntity;
import ua.logos.entity.UserEntity;
import ua.logos.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootWebAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebAppApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if(userRepository.count() == 0) {
			UserEntity user = new UserEntity();
			user.setLogin("admin");
			user.setPassword(passwordEncoder.encode("admin"));

			Set<RoleEntity> roles = new HashSet<>();
			roles.add(new RoleEntity("ADMIN"));

			user.setRoles(roles);
			userRepository.save(user);
		}


	}
}
