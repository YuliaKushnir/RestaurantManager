package org.restaurantmanager.services.auth;

import org.restaurantmanager.dto.SignupRequest;
import org.restaurantmanager.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
