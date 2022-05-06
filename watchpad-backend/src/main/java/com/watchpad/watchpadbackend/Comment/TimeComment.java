package com.watchpad.watchpadbackend.Comment;

import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.watchpad.watchpadbackend.Content.Content;
import com.watchpad.watchpadbackend.User.User;

@Data
@Entity
public class TimeComment extends Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long time_comment_id;
    private Long media_id; 
    @ManyToOne
    private User user; 
    private Timestamp comment_timestamp;
    private Timestamp media_timestamp; 
    @OneToOne
    private Content content; 
    private boolean spoiler; 
    private int likes;
    private int dislikes;

    public TimeComment(Long comment_id, Long media_id, User user, Timestamp comment_timestamp, Timestamp media_timestamp, Content content, boolean spoiler, int likes, int dislikes, Long time_comment_id) {
        super(comment_id, media_id, user, comment_timestamp, media_timestamp, content, spoiler, likes, dislikes);
        this.time_comment_id = time_comment_id;
    }

    
}