package org.restaurantmanager.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.restaurantmanager.dto.AuthenticationRequest;
import org.restaurantmanager.dto.AuthenticationResponse;
import org.restaurantmanager.dto.SignupRequest;
import org.restaurantmanager.dto.UserDto;
import org.restaurantmanager.services.auth.AuthService;
import org.restaurantmanager.services.auth.jwt.UserDetailsServiceImpl;
import org.restaurantmanager.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UserDto createdUserDto = authService.createUser(signupRequest);

        if(createdUserDto == null){
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        } catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid email or password");
        } catch(DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);

    }

}
