
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

    public ResponseEntity addComment(Comment comment) {
        Optional<Media> optionalMedia = mediaRepo.findById(comment.getMedia().getId());
        if (!optionalMedia.isPresent()) {
            Media m = new Media(comment.getMedia().getId());
            mediaRepo.save(m);
        }
        Comment newComment = commentRepo.saveAndFlush(comment);
        return new ResponseEntity(newComment, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<List<Comment>>> getCommentsByMedia(Long mediaId) {
        Optional<List<Comment>> comments = commentRepo.findByMedia(mediaId);
        if (comments.isEmpty()) {
            throw new IllegalStateException("No comment exists for that media!");
        }
        return new ResponseEntity(comments, HttpStatus.OK);

    }

    public ResponseEntity<Optional<List<Comment>>> getCommentsWithTime(Long mediaId) {
        Optional<List<Comment>> comments = commentRepo.findByTimestamp(mediaId);
        if (comments.isEmpty()) {
            throw new IllegalStateException("No comment exists for that media!");
        }
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    public ResponseEntity<String> removeComment(Comment comment) {
        commentRepo.deleteById(comment.getComment_id());

        return new ResponseEntity("Comment deleted!", HttpStatus.CREATED);

    }
}
