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
    private String followerUsername;
    private String followeeUsername;

    public Follow(){}

    public Follow(String followerUsername, String followeeUsername) {
        this.followerUsername = followerUsername;
        this.followeeUsername = followeeUsername;
    }
}
