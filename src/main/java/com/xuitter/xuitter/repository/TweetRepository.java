package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT t, COUNT(l) as tweet_likes FROM Tweet t LEFT JOIN t.likes l WHERE t.user = :user GROUP BY t ORDER BY t.createdAt DESC")
    Page<Object[]> findRecentTweetsByUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT t FROM Tweet t ORDER BY t.createdAt DESC")
    Page<Tweet> findAllRecentTweets(Pageable pageable);

    boolean existsByIdAndUser(Long id, User user);
}
