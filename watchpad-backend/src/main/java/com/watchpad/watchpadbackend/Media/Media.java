package com.watchpad.watchpadbackend.Media;
import com.watchpad.watchpadbackend.Rating.RatableEntity;

import javax.persistence.Entity;

@Entity
public class Media extends RatableEntity {

    public Media(){
        super();
    }

    public Media(Long id) {
        super(id);
    }

}
