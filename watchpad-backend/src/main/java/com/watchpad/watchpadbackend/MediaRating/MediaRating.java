package com.watchpad.watchpadbackend.MediaRating;
import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class MediaRating extends Rating {

    public MediaRating() {

    }

    @ManyToOne
    @JoinColumn(name = "externalId")
    private Media media;

    public MediaRating(User user,
                       Media media,
                       boolean isLiked) {
        super(user, media, isLiked);
    }

    public Media getMedia() {
        return media;
    }
}
