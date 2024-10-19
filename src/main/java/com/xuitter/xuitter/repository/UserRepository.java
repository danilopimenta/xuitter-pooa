package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByToken(String token);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.followers " +
            "LEFT JOIN FETCH u.following " +
            "LEFT JOIN FETCH u.tweets " +
            "WHERE u.username = :username")
    Optional<User> findUserWithDetails(@Param("username") String username);
}
