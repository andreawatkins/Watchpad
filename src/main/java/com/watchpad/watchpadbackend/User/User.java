package com.watchpad.watchpadbackend.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.watchpad.watchpadbackend.WatchList.WatchListEntry;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Data
//@Proxy(lazy=false)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String photo;

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

    public User(Long id, String email, String username, String password, String photo) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.photo = photo;
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
