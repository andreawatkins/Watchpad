package com.watchpad.watchpadbackend.Comment;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

@RestController()
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {

        return commentService.addComment(comment);
    }

    @GetMapping("/get-comments")
    public ResponseEntity<List<Comment>> getComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/get-comments-by-media")
    public ResponseEntity<Optional<List<Comment>>> getCommentByMedia(@Param("mediaId") Long mediaId) {
        return commentService.getCommentsByMedia(mediaId);
    }

    @GetMapping("/get-reviews-by-media")
    public ResponseEntity<Optional<List<Comment>>> getReviewsByMedia(@Param("mediaId") Long mediaId) {
        return commentService.getReviewsByMedia(mediaId);
    }

    @GetMapping("/get-duration-comments-by-media-sorted")
    public ResponseEntity<Optional<List<Comment>>> getDurationCommentsByMediaSorted(@Param("mediaId") Long mediaId) {
        return commentService.getDurationCommentsByMediaSorted(mediaId);
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<List<Comment>>> getCommentsWithTime(@Param("mediaId") Long mediaId) {
        return commentService.getCommentsWithTime(mediaId);
    }

    @GetMapping("/get-most-popular")
    public ResponseEntity<Optional<List<Long>>> getMostCommentedMedia() {
        return commentService.getMostCommentedMedia();
    }

    @GetMapping("/get-most-liked")
    public ResponseEntity<Optional<List<Comment>>> getMostLikedReviews(@Param("mediaId") Long mediaId) {
        return commentService.getMostLikedReviews(mediaId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removeComment(@RequestBody Comment comment) {
        return commentService.removeComment(comment);

    }

    @GetMapping("/get-reviews-by-follower")
    public ResponseEntity<Optional<List<Comment>>> getReviewsByFollow(@Param("mediaId") Long mediaId,
            @Param("username") String username) {
        return commentService.getReviewsByFollower(username, mediaId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody JSONObject values) {
        Comment comment = new Gson().fromJson(values.toJSONString(), Comment.class);
        return commentService.updateComment(id, comment);
    }

}
