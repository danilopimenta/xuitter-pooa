package com.xuitter.xuitter.service;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.repository.TweetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class XuitterService {

    private final TweetRepository tweetRepository;

    public XuitterService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
    
    public Page<Tweet> listRecentTweets(Pageable pageable) {
        return tweetRepository.findAllRecentTweets(pageable);
    }
}
