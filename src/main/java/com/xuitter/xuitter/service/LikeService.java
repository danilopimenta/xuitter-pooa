package com.xuitter.xuitter.service;

import com.xuitter.xuitter.dto.LikeResponseDTO;
import com.xuitter.xuitter.model.Like;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Page<Like> listLikesByUser(User user, Pageable pageable) {
        return likeRepository.findByUser(user, pageable);
    }

    public Page<Like> listLikesByTweet(Tweet tweet, Pageable pageable) {
        return likeRepository.findByTweet(tweet, pageable);
    }

    public void unlikeTweet(User user, Tweet tweet) {
        likeRepository.deleteByUserAndTweet(user, tweet);
    }

    public Optional<LikeResponseDTO> likeTweet(User user, Tweet tweet) {
        Like like = new Like(user, tweet);
        Optional<Like> alreadyLiked = likeRepository.findByUserAndTweet(user, tweet);
        if (alreadyLiked.isPresent()) {
            return Optional.of(new LikeResponseDTO(alreadyLiked.get().getId(), tweet.getId(), user.getId()));
        }
        like = likeRepository.save(like);
        return Optional.of(new LikeResponseDTO(like.getId(), tweet.getId(), user.getId()));
    }
}