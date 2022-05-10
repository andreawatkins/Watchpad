package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Media media;
    private LocalDateTime timestamp;

    @OneToOne
    private User user;

    public WatchList(User user, Media media) {
        this.user = user;
        this.media = media;
        timestamp = LocalDateTime.now();
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Media getMedia() {
        return media;
    }

    public WatchList() {

    }


    public String toString() {
        return media.toString() + " " + timestamp.toString();
    }


}
