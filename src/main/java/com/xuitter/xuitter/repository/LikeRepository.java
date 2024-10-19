package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Like;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Page<Like> findByUser(User user, Pageable pageable);

    Page<Like> findByTweet(Tweet tweet, Pageable pageable);

    Optional<Like> findByUserAndTweet(User user, Tweet tweet);

    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.user = :user AND l.tweet = :tweet")
    void deleteByUserAndTweet(@Param("user") User user, @Param("tweet") Tweet tweet);
}