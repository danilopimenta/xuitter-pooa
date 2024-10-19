package com.xuitter.xuitter.service;

import com.xuitter.xuitter.model.Follower;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.repository.FollowerRepository;
import com.xuitter.xuitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Follower> listFollowersByUser(Long userId, Pageable pageable) {
        return followerRepository.findByUserId(userId, pageable);
    }

    public void followUser(User user, Long userToFollow) {
        User userFollowed = userRepository.findById(userToFollow).orElseThrow(() -> new RuntimeException("User not found"));
        followerRepository.save(new Follower(user, userFollowed));
    }

    public void unfollowUser(User user, Long userToUnfollow) {
        User userUnfollowed = userRepository.findById(userToUnfollow).orElseThrow(() -> new RuntimeException("User not found"));
        followerRepository.deleteByUserAndUserFollowed(user, userUnfollowed);
    }
}
