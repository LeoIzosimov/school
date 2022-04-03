package com.jopov.schoolportal.secutity.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private final List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
