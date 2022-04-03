package com.jopov.schoolportal.secutity.controllers;

import com.jopov.schoolportal.secutity.response.JwtResponse;
import com.jopov.schoolportal.secutity.services.UserService;
import com.jopov.schoolportal.secutity.models.Role;
import com.jopov.schoolportal.secutity.models.User;
import com.jopov.schoolportal.secutity.jwt.JWTUtils;
import com.jopov.schoolportal.secutity.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class SignInController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User userDetails = (User) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUsernameAndRole(username, userDetails.getRoles());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new JwtResponse(token,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getRoles().stream()
                            .map(Role::getAuthority).collect(Collectors.toList())));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Неверные имя пользователя или пароль!");
        }
    }
}
