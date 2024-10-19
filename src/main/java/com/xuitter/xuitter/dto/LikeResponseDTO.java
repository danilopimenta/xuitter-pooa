package com.xuitter.xuitter.dto;

public class LikeResponseDTO {
    private Long id;
    private Long tweetId;
    private Long userId;

    public LikeResponseDTO(Long id, Long tweetId, Long userId) {
        this.id = id;
        this.tweetId = tweetId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public Long getUserId() {
        return userId;
    }
}