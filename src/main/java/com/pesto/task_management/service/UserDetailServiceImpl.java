package com.pesto.task_management.service;

import com.pesto.task_management.dto.UserDto;
import com.pesto.task_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * This method loads user details by username.
     * It receives a username and uses UserRepository to retrieve the user details.
     * If the user is not found, it throws a UsernameNotFoundException.
     * @param username - The username of the user.
     * @return UserDetails object containing user details.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("No user found" + username));
        return new org.springframework.security.core.userdetails.User(userDto.getUserName(), userDto.getPassword(), true, true, true, true, getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
