package org.restaurantmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class RestaurantManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestaurantManagerApplication.class, args);

    }

}
