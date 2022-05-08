package com.watchpad.watchpadbackend.Rating;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public RatableEntity() {

    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RatableEntity{" +
                "id=" + id +
                '}';
    }
}
