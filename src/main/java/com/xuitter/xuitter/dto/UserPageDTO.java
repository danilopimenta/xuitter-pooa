package com.xuitter.xuitter.dto;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserPageDTO {
    private User user;
    private Integer followers;
    private Integer following;
    private Page<Tweet> tweets;

    public UserPageDTO(User user, Integer followers, Integer following, Page<Tweet> tweets) {
        this.user = user;
        this.followers = followers;
        this.following = following;
        this.tweets = tweets;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public String getBio() {
        return this.user.getBio();
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public List<TweetDTO> getTweets() {
        return tweets.getContent().stream()
                .map(tweet -> new TweetDTO(tweet.getId(), tweet.getContent(), tweet.getLikes().size()))
                .collect(Collectors.toList());
    }

    public int getTweetsCount() {
        return tweets.getContent().size();
    }

    public static class TweetDTO {
        private Long id;
        private String content;
        private int likes;

        public TweetDTO(Long id, String content, int likes) {
            this.id = id;
            this.content = content;
            this.likes = likes;
        }

        public Long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public int getLikes() {
            return likes;
        }
    }
}
