package com.watchpad.watchpadbackend.Media;

import com.watchpad.watchpadbackend.Comment.Comment;
import com.watchpad.watchpadbackend.Rating.RatableEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Media extends RatableEntity {

    private String externalId;
    

    public Media(Long externalId){
        super();
        this.setId(externalId);
    }

    public void setExternalId(String id) {
        this.externalId = id;
    }

    public Media() {
        super();
    }

    public String getExternalId() {
        return externalId;
    }



    @Override
    public String toString() {
        return "Media{" +
                "Id='" + super.getId() + '\'' +
                "externalId='" + externalId +
                '}';
    }


}
