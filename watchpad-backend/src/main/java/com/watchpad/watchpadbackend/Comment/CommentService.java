
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

    public ResponseEntity addComment(Comment comment){
        Optional<Media> optionalMedia = mediaRepo.findById(comment.getMedia().getId());
        if(!optionalMedia.isPresent()){
            Media m = new Media(comment.getMedia().getId());
            mediaRepo.save(m);
        }
         commentRepo.save(comment); 

         return new ResponseEntity("Comment created!", HttpStatus.CREATED);
       


    }

    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Comment>> getCommentsByMediaId(Long id) {
        return new  ResponseEntity<>(commentRepo.findByMediaId(id), HttpStatus.OK); 

    }
}

