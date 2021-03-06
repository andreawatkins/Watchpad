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
    @JsonBackReference(value = "rating-user")
    User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("ratableEntityId")
    @JoinColumn(name = "ratableEntity_id")
    RatableEntity ratableEntity;


    public Rating() {
    }

    public Rating(User user) {
        this.user = user;
    }

    public void setRatableEntity(RatableEntity ratableEntity) {
        this.ratableEntity = ratableEntity;
    }

    public User getUser() {
        return user;
    }

    public RatableEntity getRatableEntity() {
        return ratableEntity;
    }

    public RatingKey getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", ratableEntity=" + ratableEntity +
                '}';
    }


}
