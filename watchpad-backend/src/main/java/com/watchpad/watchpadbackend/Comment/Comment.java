package com.watchpad.watchpadbackend.Comment;

import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.watchpad.watchpadbackend.Content.Content;
import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import com.watchpad.watchpadbackend.User.User;


@Data
@Entity
@Table(name="comments")
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;
    @ManyToOne
    @JoinColumn(name = "id")
    private Media media; 
    @ManyToOne
    private User user; 
    private LocalDateTime comment_timestamp;
    //@OneToOne
    @Column(columnDefinition = "TEXT")
    private String content; 
    private boolean spoiler; 
    
    

    public Comment(Long comment_id, Media media, User user, LocalDateTime comment_timestamp
    , String content, boolean spoiler, int likes, int dislikes ){

        this.comment_id=comment_id;
        this.media=media;
        this.user=user;
        this.comment_timestamp=comment_timestamp;
        
        this.content = content;
        this.spoiler = spoiler;
       
     }

     public Comment(){};

     @Override
     public String toString() {
       return "Comment{" + "id=" + this.comment_id + ", media_id='" + this.media + '\'' + ", user='" + this.user + '\'' +
        ", timestamp='" + this.comment_timestamp + '\'' + ", content='" + this.content + '\'' + ", spoiler='" + this.spoiler + '\'' +
         + '}';
     }



}



    





    

