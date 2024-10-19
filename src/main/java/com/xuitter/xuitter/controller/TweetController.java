package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.dto.TweetResponseDTO;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping
    public ResponseEntity<TweetResponseDTO> createTweet(@RequestBody Tweet tweet, @AuthenticationPrincipal User user) {
        try {
            Tweet newTweet = this.tweetService.createTweet(tweet, user);
            TweetResponseDTO tweetResponseDTO = TweetResponseDTO.fromTweet(newTweet);
            return ResponseEntity.ok(tweetResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetResponseDTO> getTweet(@PathVariable Long id) {
        Optional<Tweet> tweetOptional = this.tweetService.getTweet(id);
        if (tweetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Tweet tweet = tweetOptional.get();
        TweetResponseDTO tweetResponseDTO = TweetResponseDTO.fromTweet(tweet);
        return ResponseEntity.ok(tweetResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long id, @AuthenticationPrincipal User user) {
        try {
            this.tweetService.deleteTweet(id, user);
            return ResponseEntity.ok("{\"delete\": \"success\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TweetResponseDTO> updateTweet(@PathVariable Long id, @RequestBody Tweet tweet, @AuthenticationPrincipal User user) {
        Optional<Tweet> tweetOptional = this.tweetService.updateTweet(id, tweet, user);
        if (tweetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Tweet updatedTweet = tweetOptional.get();
        TweetResponseDTO tweetResponseDTO = TweetResponseDTO.fromTweet(updatedTweet);
        return ResponseEntity.ok(tweetResponseDTO);
    }
}
