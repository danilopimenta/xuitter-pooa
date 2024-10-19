package com.xuitter.xuitter.service;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public Page<Object[]> listTweetsByUser(User user, Pageable pageable) {
        return tweetRepository.findRecentTweetsByUser(user, pageable);
    }

    public Optional<Tweet> getTweet(Long id) {
        return tweetRepository.findById(id);
    }

    public Tweet saveTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public void deleteTweet(Long id, User user) {
        if (tweetRepository.existsByIdAndUser(id, user)) {
            tweetRepository.deleteById(id);
        }
        throw new RuntimeException("Tweet não encontrado ou não pertence ao usuário.");
    }

    public Tweet createTweet(Tweet tweet, User user) {
        tweet.setUser(user);
        return tweetRepository.save(tweet);
    }

    public Optional<Tweet> updateTweet(Long id, Tweet tweet, User user) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (tweetOptional.isEmpty()) {
            return Optional.empty();
        }
        Tweet tweetToUpdate = tweetOptional.get();
        if (!tweetToUpdate.getUser().equals(user)) {
            return Optional.empty();
        }
        tweetToUpdate.setContent(tweet.getContent());
        return Optional.of(tweetRepository.save(tweetToUpdate));
    }
}
