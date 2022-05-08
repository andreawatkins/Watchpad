package com.watchpad.watchpadbackend.Rating;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;

@Table
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private RatableEntity ratableEntity;

    private boolean rating;

    public Rating() {

    }

    public Rating(User user, RatableEntity ratableEntity, boolean rating) {
        this.user = user;
        this.ratableEntity = ratableEntity;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public RatableEntity getRatableEntity() {
        return ratableEntity;
    }

    public boolean getRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", ratableEntity=" + ratableEntity +
                ", rating=" + rating +
                '}';
    }
}
