package com.xuitter.xuitter.dto;

import com.xuitter.xuitter.model.Tweet;

public class TweetResponseDTO {
    private Long id;
    private String content;
    private String author;

    public TweetResponseDTO(Long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public static TweetResponseDTO fromTweet(Tweet tweet) {
        return new TweetResponseDTO(tweet.getId(), tweet.getContent(), tweet.getUser().getUsername());
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
