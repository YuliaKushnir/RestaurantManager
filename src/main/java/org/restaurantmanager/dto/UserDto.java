package org.restaurantmanager.dto;

import lombok.Data;
import org.restaurantmanager.enums.UserRole;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole role;

}
