package com.xuitter.xuitter.repository;


import com.xuitter.xuitter.model.Retweet;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    List<Retweet> findByUser(User user);

    List<Retweet> findByTweet(Tweet tweet);

    Optional<Retweet> findByUserAndTweet(User user, Tweet tweet);

    Long countByTweet(Tweet tweet);
}
