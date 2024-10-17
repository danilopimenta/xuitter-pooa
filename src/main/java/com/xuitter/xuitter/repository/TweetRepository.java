package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query("SELECT t FROM Tweet t WHERE t.user = :user ORDER BY t.createdAt DESC")
    List<Tweet> findRecentTweetsByUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT t FROM Tweet t ORDER BY t.createdAt DESC")
    List<Tweet> findAllRecentTweets();
}
