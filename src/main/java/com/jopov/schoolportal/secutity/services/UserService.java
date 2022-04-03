package com.jopov.schoolportal.secutity.services;

import com.jopov.schoolportal.secutity.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void saveUser (User user);

    boolean existsByUsername(String username);
}
