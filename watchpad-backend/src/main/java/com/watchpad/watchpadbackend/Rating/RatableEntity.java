package com.watchpad.watchpadbackend.Rating;

import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RatableEntity {

    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    Set<User> ratedBy = new HashSet<>();

    @OneToMany(mappedBy = "ratableEntity", fetch = FetchType.LAZY)
    Set<Rating> ratings = new HashSet<>();

    public RatableEntity() {

    }

    public void addRating(Rating rating){
        ratings.add(rating);
        rating.setRatableEntity(this);
    }
    public RatableEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newId){
        this.id = newId;
    }
    @Override
    public String toString() {
        return "RatableEntity{" +
                "id=" + id +
                '}';
    }
}
