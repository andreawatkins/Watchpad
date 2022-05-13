package com.watchpad.watchpadbackend.Rating;

import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RatableEntity {

    @Id
    private Long id;

    @ManyToMany
    Set<User> ratedBy;

    @OneToMany(mappedBy = "ratableEntity")
    Set<Rating> ratings;

    public RatableEntity() {

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
