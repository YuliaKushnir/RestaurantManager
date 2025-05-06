package org.restaurantmanager.services.auth;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.restaurantmanager.dto.SignupRequest;
import org.restaurantmanager.dto.UserDto;
import org.restaurantmanager.enums.UserRole;
import org.restaurantmanager.models.User;
import org.restaurantmanager.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);

        if(adminAccount == null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@test.com");
            user.setPassword(bCryptPasswordEncoder.encode("admin"));
            user.setRole(UserRole.ADMIN);

            userRepository.save(user);
        }

    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.USER);

        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setName(createdUser.getName());
        userDto.setEmail(createdUser.getEmail());
        userDto.setRole(createdUser.getRole());
        return userDto;
    }
}
