package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Like;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user);

    List<Like> findByTweet(Tweet tweet);

    Optional<Like> findByUserAndTweet(User user, Tweet tweet);

    Long countByTweet(Tweet tweet);
}
