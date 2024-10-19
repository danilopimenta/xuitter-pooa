package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.model.Follower;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/followers")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Follower>> listUserFollowers(
            @PathVariable Long id, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(followerService.listFollowersByUser(id, pageable));
    }

    @PostMapping("/follow/user/{userToFollow}")
    public ResponseEntity<Void> followUser(@PathVariable Long userToFollow, @AuthenticationPrincipal User user) {
        followerService.followUser(user, userToFollow);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unfollow/user/{userToUnfollow}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long userToUnfollow, @AuthenticationPrincipal User user) {
        followerService.unfollowUser(user, userToUnfollow);
        return ResponseEntity.ok().build();
    }
}
