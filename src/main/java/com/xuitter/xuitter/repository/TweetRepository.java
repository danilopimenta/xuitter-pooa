package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    // Busca os tweets recentes de um usuário com paginação
    @Query("SELECT t FROM Tweet t WHERE t.user = :user ORDER BY t.createdAt DESC")
    Page<Tweet> findRecentTweetsByUser(@Param("user") User user, Pageable pageable);

    // Busca todos os tweets recentes com paginação
    @Query("SELECT t FROM Tweet t ORDER BY t.createdAt DESC")
    Page<Tweet> findAllRecentTweets(Pageable pageable);
}
