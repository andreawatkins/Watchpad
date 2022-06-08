package com.watchpad.watchpadbackend.Media;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Proxy(lazy=false)
@Table(name = "media")
public class Media extends RatableEntity {

    public Media(){
        super();
    }

    public Media(Long id) {
        super(id);
    }

}
