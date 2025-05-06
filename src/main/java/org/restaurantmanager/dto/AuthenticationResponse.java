package org.restaurantmanager.dto;

import lombok.Data;
import org.restaurantmanager.enums.UserRole;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole role;

    private Long userId;

}
