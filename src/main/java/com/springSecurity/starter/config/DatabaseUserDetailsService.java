package com.springSecurity.starter.config;

import com.springSecurity.starter.model.UserModel;
import com.springSecurity.starter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findUserModelByUsername(username);
        return userModel.map(UserModelUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", username)));
    }
}
