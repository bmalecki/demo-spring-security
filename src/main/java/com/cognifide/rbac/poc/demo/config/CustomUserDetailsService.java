package com.cognifide.rbac.poc.demo.config;

import com.cognifide.rbac.poc.demo.users.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userService.getUserById(username).map(
                user -> new User(username, user.getPassword(),
                        true, true, true, true,
                        user.getRoles().stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                                .collect(Collectors.toList())
                ))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
