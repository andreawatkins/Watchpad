package com.watchpad.watchpadbackend.Comment;

import java.util.List;

import com.watchpad.watchpadbackend.User.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal User user){
        
        Comment comment = commentService.addComment(commentDto, user);
        System.out.println(commentDto);
        return ResponseEntity.ok(comment); 

    }

    @GetMapping("/get-comments")
    public ResponseEntity<List<Comment>> getComments(){
        return commentService.getAllComments();
    }

   /*  @GetMapping("/get-comments-by-media")
    public ResponseEntity<Comment> getCommentByMediaId(@PathVariable("mediaId") String mediaId){
        return commentService.getCommentByMediaId(mediaId);
    } */

       @GetMapping("/get-comments-by-id{commentId}")
    public ResponseEntity<Comment> getCommentByCommentId(@PathVariable("commentId") Long comment_Id){
        return commentService.getCommentByCommentId(comment_Id);
    } 


}
