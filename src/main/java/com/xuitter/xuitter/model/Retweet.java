package com.xuitter.xuitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "retweets")
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @NotNull(message = "Tweet cannot be null")
    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public Retweet() {
        this.createdAt = LocalDateTime.now();
    }

    public Retweet(User user, Tweet tweet) {
        this.user = user;
        this.tweet = tweet;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Retweet retweet = (Retweet) o;
        return Objects.equals(id, retweet.id) &&
                Objects.equals(user, retweet.user) &&
                Objects.equals(tweet, retweet.tweet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tweet);
    }

    @Override
    public String toString() {
        return "Retweet{" +
                "id=" + id +
                ", user=" + user +
                ", tweet=" + tweet +
                ", createdAt=" + createdAt +
                '}';
    }

}
