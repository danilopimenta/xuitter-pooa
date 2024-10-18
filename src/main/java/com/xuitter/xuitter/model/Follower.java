package com.xuitter.xuitter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @NotNull(message = "Follower cannot be null")
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    public Follower(){

    }

    public Follower(User user, User follower){
        this.user = user;
        this.follower = follower;
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

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follower follower1 = (Follower) o;
        return Objects.equals(id, follower1.id) &&
                Objects.equals(user, follower1.user) &&
                Objects.equals(follower, follower1.follower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, follower);
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", user=" + user +
                ", follower=" + follower +
                '}';
    }
}



