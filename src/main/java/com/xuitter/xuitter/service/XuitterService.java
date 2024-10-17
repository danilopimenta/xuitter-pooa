package com.xuitter.xuitter.service;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XuitterService {
    private final TweetRepository tweetRepository;

    public XuitterService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> list() {
        return tweetRepository.findAllRecentTweets();
    }
}
