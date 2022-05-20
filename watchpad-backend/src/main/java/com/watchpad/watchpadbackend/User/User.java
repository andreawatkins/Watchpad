package com.watchpad.watchpadbackend.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.MediaRating.MediaRating;
import com.watchpad.watchpadbackend.WatchList.WatchListEntry;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String photo;

    @ManyToMany
    Set<Media> ratedMedia;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    Set<MediaRating> mediaRatings;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }
    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void addMediaToWatchList(WatchListEntry media) {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
