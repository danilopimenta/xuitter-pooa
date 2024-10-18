package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Follower;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Page<Follower> findByUser(User user, Pageable pageable);

    Page<Follower> findByFollower(User follower, Pageable pageable);

    Optional<Follower> findByUserAndFollower(User user, User follower);

    Long countByUser(User user);

    Long countByFollower(User follower);

    void deleteByUserAndFollower(User user, User follower);
}