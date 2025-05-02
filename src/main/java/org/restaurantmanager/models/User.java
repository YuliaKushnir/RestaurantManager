package org.restaurantmanager.models;

import jakarta.persistence.*;
import lombok.Data;
import org.restaurantmanager.enums.UserRole;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole role;

}
