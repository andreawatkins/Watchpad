package com.watchpad.watchpadbackend.Comment;

import lombok.Data;

@Data
public class CommentDto{

    private Long mediaId; 
    private String content;
    private String user; 
    private boolean spoiler; 

    CommentDto(Long mediaId, String content, String user, boolean spoiler){
        this.mediaId = mediaId;
        this.content = content;
        this.user = user; 
        this.spoiler = spoiler;
    }

    public boolean getSpoiler() {
        return false;
    }

    
}