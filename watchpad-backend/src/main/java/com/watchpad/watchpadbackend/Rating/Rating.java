package com.watchpad.watchpadbackend.Rating;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private RatableEntity ratableEntity;

    private boolean isLiked;

    public Rating() {

    }

    public Rating(User user, RatableEntity ratableEntity, boolean isLiked) {
        this.user = user;
        this.ratableEntity = ratableEntity;
        this.isLiked = isLiked;
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
                ", rating=" + isLiked +
                '}';
    }
}
