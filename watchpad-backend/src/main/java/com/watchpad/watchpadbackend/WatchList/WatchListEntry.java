package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "watchlist_entries")
public class WatchListEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchlist_id;

    @OneToOne
    private Media media;
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public WatchListEntry(Long watchlist_id, User user, Media media) {
        this.watchlist_id = watchlist_id;
        this.user = user;
        this.media = media;
        timestamp = LocalDateTime.now();
    }

    public WatchListEntry() {

    }


    @Override
    public String toString() {
        return "WatchList{" +
                "id=" + watchlist_id +
                ", user='" + user + '\'' +
                ", media='" + media + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public Long getId() {
        return watchlist_id;
    }
}
