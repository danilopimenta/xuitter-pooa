package com.xuitter.xuitter.service;

import com.xuitter.xuitter.dto.UserPageDTO;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.repository.TweetRepository;
import com.xuitter.xuitter.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public User registerNewUser(String username, String email, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username já está em uso.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já está em uso.");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    public String generateToken(User user) {
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setTokenExpirationTime(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora
        userRepository.save(user);
        return token;
    }

    public User isTokenValid(String token) {
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getTokenExpirationTime().isAfter(LocalDateTime.now())) {
                return user;
            }
        }
        return null;
    }

    public User authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserPageDTO getUserPage(String username, Pageable pageable) {
        User user = userRepository.findUserWithDetails(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Integer followers = user.getFollowers().size();
        int following = user.getFollowing().size();
        System.out.println(this.tweetRepository.findRecentTweetsByUser(user, pageable));
        Page<Tweet> tweets = this.tweetRepository.findRecentTweetsByUser(user, pageable)
                .map(objects -> (Tweet) objects[0]);
        return new UserPageDTO(user, followers, following, tweets);
    }

    public void updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setBio(user.getBio());
        userRepository.save(userToUpdate);
    }
}
