package com.watchpad.watchpadbackend.CommentLike;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watchpad.watchpadbackend.Comment.Comment;
import com.watchpad.watchpadbackend.User.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_like")
public class CommentLike {

    @EmbeddedId
    CommentLikeKey id = new CommentLikeKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("commentId")
    @JoinColumn(name = "comment_id")
    @JsonBackReference(value = "comment-commentLikes")
    Comment comment;

    private boolean isLiked;

    public CommentLike(){
    }

    public CommentLike(User user, Comment comment, boolean isLiked) {
        this.user = user;
        this.comment = comment;
        this.isLiked = isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public CommentLikeKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Comment getComment() {
        return comment;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "id=" + id +
                ", user=" + user +
                ", comment=" + comment +
                ", isLiked=" + isLiked +
                '}';
    }
}
