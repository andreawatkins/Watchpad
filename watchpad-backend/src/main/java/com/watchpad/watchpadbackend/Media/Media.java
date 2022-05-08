package com.watchpad.watchpadbackend.Media;

import com.watchpad.watchpadbackend.Comment.Comment;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Media extends RatableEntity {

    private String externalId;
    @OneToMany
    private List<Comment> comments;

    public Media(String externalId){
        super();
        this.externalId = externalId;
    }

    public Media() {
        super();
    }

    @Override
    public String toString() {
        return "Media{" +
                "comments=" + comments +
                '}';
    }
}
