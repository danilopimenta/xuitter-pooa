package com.xuitter.xuitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 280)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createdAt = new java.util.Date(System.currentTimeMillis());

    @ManyToOne
    @JsonIgnoreProperties("tweets")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "tweets_likes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likes;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = java.util.Date.from(createdAt.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}
