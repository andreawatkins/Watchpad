package com.watchpad.watchpadbackend.Comment;

import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.*;

import com.watchpad.watchpadbackend.Content.Content;
import com.watchpad.watchpadbackend.User.User;


@Data
@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;
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
    

    public Comment(Long comment_id, Long media_id, User user, Timestamp comment_timestamp,
     Timestamp media_timestamp, Content content, boolean spoiler, int likes, int dislikes ){

        this.comment_id=comment_id;
        this.media_id=media_id;
        this.user=user;
        this.comment_timestamp=comment_timestamp;
        this.media_timestamp=media_timestamp;
        this.content = content;
        this.spoiler = spoiler;
        this.likes=likes;
        this.dislikes=dislikes; 
     }

     public Comment(){};

}



    





    

