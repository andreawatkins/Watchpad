package com.watchpad.watchpadbackend.Media;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;

@Entity
@Proxy(lazy=false)
public class Media extends RatableEntity {

    public Media(){
        super();
    }

    public Media(Long id) {
        super(id);
    }

}
