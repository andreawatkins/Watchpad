package com.watchpad.watchpadbackend.Follow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Follow {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Long follower_id;
    private Long followee_id;

    public Follow(){}

    public Follow(Long follower_id, Long followee_id) {
        this.follower_id = follower_id;
        this.followee_id = followee_id;
    }
}
