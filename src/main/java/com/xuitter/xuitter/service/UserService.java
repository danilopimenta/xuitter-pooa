package com.xuitter.xuitter.service;

import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(String username, String email, String rawPassword) {
        if(userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username já está em uso.");
        }
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já está em uso.");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }
}
