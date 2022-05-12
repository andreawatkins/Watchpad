package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WatchListEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Media media;
    private LocalDateTime timestamp;

    @ManyToOne
    private User user;

    public WatchListEntry(User user, Media media) {
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

    public WatchListEntry() {

    }

    @Override
    public String toString() {
        return "WatchList{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", media='" + media + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
