package com.watchpad.watchpadbackend.MediaRating;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;

@Entity
@Table
public class MediaRating extends Rating {

    private Float rating;

    public MediaRating() {

    }

    public MediaRating(User user, Float rating) {
        super(user);
        this.rating = rating;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
