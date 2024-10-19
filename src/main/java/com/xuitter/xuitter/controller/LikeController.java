package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.dto.LikeResponseDTO;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.repository.TweetRepository;
import com.xuitter.xuitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private TweetRepository tweetRepository;

    @PostMapping("/tweet/{tweetId}")
    public ResponseEntity<Optional<LikeResponseDTO>> likeTweet(@AuthenticationPrincipal User user, @PathVariable Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet not found"));
        return ResponseEntity.ok(likeService.likeTweet(user, tweet));
    }

    @DeleteMapping("/tweet/{tweetId}")
    public ResponseEntity<String> unlikeTweet(@AuthenticationPrincipal User user, @PathVariable Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet not found"));
        try {
            likeService.unlikeTweet(user, tweet);
            return ResponseEntity.ok("{\"unlike\": \"success\"}");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
