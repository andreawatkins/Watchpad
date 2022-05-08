package com.watchpad.watchpadbackend.MediaRating;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class MediaRating extends Rating {

    public MediaRating() {

    }

    public MediaRating(User user,
                       RatableEntity ratableEntity,
                       boolean isLiked) {
        super(user, ratableEntity, isLiked);
    }

}
