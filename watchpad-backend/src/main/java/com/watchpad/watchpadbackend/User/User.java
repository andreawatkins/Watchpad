package com.watchpad.watchpadbackend.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.watchpad.watchpadbackend.WatchList.WatchListEntry;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    private List<WatchListEntry> watchListEntry;

    public User() {
    }



    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        watchListEntry = new ArrayList<>();
    }

    public void addMediaToWatchList(WatchListEntry media) {
        watchListEntry.add(media);
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
