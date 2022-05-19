package com.watchpad.watchpadbackend.Comment;

import java.util.List;
import java.util.Optional;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/comments")
public class CommentController {

@Autowired    
private final CommentService commentService; 

public CommentController(CommentService commentService) {
    this.commentService = commentService;
}
@PostMapping("")
public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
    
    return commentService.addComment(comment); 
}


    @GetMapping("/get-comments")
    public ResponseEntity<List<Comment>> getComments(){
        return commentService.getAllComments();
    }

   @GetMapping("/get-comments-by-media")
    public ResponseEntity <Optional<List<Comment>>> getCommentByMedia(@Param("mediaId") Long mediaId) {
        return commentService.getCommentsByMedia(mediaId);
    } 



}
