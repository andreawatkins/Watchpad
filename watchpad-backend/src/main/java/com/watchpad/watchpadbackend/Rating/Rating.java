package com.watchpad.watchpadbackend.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Rating {

    @EmbeddedId
    RatingKey id = new RatingKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("ratableEntityId")
    @JoinColumn(name = "ratableEntity_id")
    RatableEntity ratableEntity;

    private boolean isLiked;

    public Rating() {
    }

    public Rating(User user, RatableEntity ratableEntity, boolean isLiked) {
        this.user = user;
        this.ratableEntity = ratableEntity;
        this.isLiked = isLiked;
    }

    public User getUser() {
        return user;
    }

    public RatableEntity getRatableEntity() {
        return ratableEntity;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean rating) {
        this.isLiked = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", ratableEntity=" + ratableEntity +
                ", isLiked=" + isLiked +
                '}';
    }
}
