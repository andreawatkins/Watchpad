package com.watchpad.watchpadbackend.Comment;

import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;

import com.watchpad.watchpadbackend.Content.Content;
import com.watchpad.watchpadbackend.User.User;

@Data
public class TimeComment extends Comment{

    private Long comment_id;
    private Long media_id; 
    private User user; 
    private LocalDateTime comment_timestamp;
    private Timestamp media_timestamp; 
    private String content; 
    private boolean spoiler; 
    private int likes;
    private int dislikes;


public TimeComment(Long comment_id, Long media_id, User user, Timestamp comment_timestamp, Timestamp media_timestamp, 
String content, boolean spoiler, int likes, int dislikes, Long time_comment_id) {

}
    
}