
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

    public ResponseEntity<Comment> getCommentById(Long id) {
        return new ResponseEntity(commentRepo.findById(id), HttpStatus.OK);
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

        return new ResponseEntity(comment, HttpStatus.CREATED);

    }

    public ResponseEntity<Optional<List<Long>>> getMostCommentedMedia() {
        Optional<List<Long>> mediaIds = commentRepo.findMostCommentedMedia();
        if (mediaIds.isEmpty()) {
            throw new IllegalStateException("No media exists!");
        }
        return new ResponseEntity(mediaIds, HttpStatus.OK);

    }

    public ResponseEntity<Optional<List<Comment>>> getMostLikedComments(Long mediaId) {
        Optional<List<Comment>> comments = commentRepo.findMostLikedComments(mediaId);
        if (comments.isEmpty()) {
            throw new IllegalStateException("No comment exists for that media!");
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }
    public ResponseEntity<Optional<List<Comment>>> getReviewsByMedia(Long mediaId) {
        Optional<List<Comment>> comments = commentRepo.findReviewsByMedia(mediaId);
        if (comments.isEmpty()) {
            throw new IllegalStateException("No comments exist for that media!");
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    public ResponseEntity<Optional<List<Comment>>> getDurationCommentsByMediaSorted(Long mediaId) {
         Optional<List<Comment>> comments = commentRepo.findDurationCommentsByMediaSorted(mediaId);
        if (comments.isEmpty()) {
            throw new IllegalStateException("No comments exist for that media!");
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }
    

    public ResponseEntity<String> updateComment(Long comment_id, Comment newComment) {
        try {
            Comment oldComment = commentRepo.findById(comment_id).get();
            
            oldComment.setContent(newComment.getContent());
            oldComment.setSpoiler(newComment.isSpoiler());
           
            commentRepo.save(oldComment);
            return new ResponseEntity(oldComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
