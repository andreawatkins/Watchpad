package com.watchpad.watchpadbackend.Rating;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RatableEntity {

    @Id
    private Long id;

    public RatableEntity() {

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
