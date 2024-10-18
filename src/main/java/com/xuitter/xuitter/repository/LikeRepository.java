package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Like;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Page<Like> findByUser(User user, Pageable pageable);

    Page<Like> findByTweet(Tweet tweet, Pageable pageable);

    Optional<Like> findByUserAndTweet(User user, Tweet tweet);

    Long countByTweet(Tweet tweet);

    void deleteByUserAndTweet(User user, Tweet tweet);

    boolean existsByUserAndTweet(User user, Tweet tweet);
}