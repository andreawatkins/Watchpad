package com.watchpad.watchpadbackend.MediaLike;

import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class MediaLike extends Rating {
    private boolean isLiked;

    public MediaLike() {
    }

    public MediaLike(User user, boolean isLiked) {
        super(user);
        this.isLiked = isLiked;
    }

    public void setIsLiked(boolean updateLiked) {
        this.isLiked = updateLiked;
    }

    public boolean getIsLiked() {
        return isLiked;
    }
}

