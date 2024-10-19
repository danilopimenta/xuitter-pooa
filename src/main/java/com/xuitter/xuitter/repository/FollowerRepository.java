package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Follower;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Page<Follower> findByUserId(Long userId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follower f WHERE f.user = :user AND f.follower = :follower")
    void deleteByUserAndUserFollowed(@Param("user") User user, @Param("follower") User userUnfollowed);
}