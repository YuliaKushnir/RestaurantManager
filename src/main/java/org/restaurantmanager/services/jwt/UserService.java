package org.restaurantmanager.services.jwt;

import org.restaurantmanager.dto.CategoryDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

}
