package com.xuitter.xuitter.repository;

import com.xuitter.xuitter.model.Follower;
import com.xuitter.xuitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    List<Follower> findByUser(User user);

    List<Follower> findByFollower(User follower);

    Optional<Follower> findByUserAndFollower(User user, User follower);

    Long countByUser(User user);

    Long countByFollower(User follower);
}
