package com.watchpad.watchpadbackend.Follow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "follow")
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
