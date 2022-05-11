package com.watchpad.watchpadbackend.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.Comment.CommentDto;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired 
    private MediaRepository mediaRepo;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepo = commentRepository; 
    }

    public Comment addComment(CommentDto commentDto, User user){
        Comment comment = new Comment(); 
        Media media = mediaRepo.getById(commentDto.getMediaId()); 
        comment.setContent(commentDto.getContent()); 
        comment.setUser(user);
        comment.setComment_timestamp(LocalDateTime.now());
        comment.setSpoiler(commentDto.getSpoiler()); 
        comment.setMedia(media); 

        return commentRepo.save(comment); 
    }

    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentRepo.findAll(), HttpStatus.OK);
    }

   /*  public ResponseEntity<Comment> getCommentByMediaId(String mediaId){
        Optional<Comment> commentOptional = commentRepo.findByMediaId(mediaId);
        if(commentOptional.isEmpty()){
            throw new IllegalStateException("No comments on that media!");
        }

        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    } */

       public ResponseEntity<Comment> getCommentByCommentId(Long comment_id){
        Optional<Comment> commentOptional = commentRepo.findByCommentId(comment_id);
        if(commentOptional.isEmpty()){
            throw new IllegalStateException("No comment with that id!");
        }

        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    } 
   

}
