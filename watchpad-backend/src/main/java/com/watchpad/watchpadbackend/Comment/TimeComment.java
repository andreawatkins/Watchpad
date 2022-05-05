package com.watchpad.watchpadbackend.TimeComment;

import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;

import com.watchpad.watchpadbackend.Content.Content;
import com.watchpad.watchpadbackend.User.User;

@Data
public class TimeComment extends Comment{

    private Long comment_id;
    private Long media_id; 
    private User user; 
    private Timestamp comment_timestamp;
    private Timestamp media_timestamp; 
    private Content content; 
    private boolean spoiler; 
    private int likes;
    private int dislikes;



    
}