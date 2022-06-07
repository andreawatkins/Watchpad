package com.watchpad.watchpadbackend.CommentLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path="api/comments/likes")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @Autowired
    public CommentLikeController(CommentLikeService commentRatingService) {
        this.commentLikeService = commentRatingService;
    }

    @GetMapping("/get-like")
    public ResponseEntity<Optional<CommentLike>> getCommentLike(@Param("userId") Long userId,
                                                                @Param("commentId") Long commentId){
        return commentLikeService.getLike(userId, commentId);
    }

    @GetMapping("/count-likes")
    public ResponseEntity<Long> getCountOfLikesByCommentId(@Param("commentId") Long commentId){
        return commentLikeService.countLikesForComment(commentId);
    }

    @GetMapping("/count-dislikes")
    public ResponseEntity<Long> getCountOfDislikesByEntityId(@Param("commentId") Long commentId){
        return commentLikeService.countDislikesForComment(commentId);
    }

    @PostMapping("/save-like")
    public ResponseEntity<String> saveNewLike(@Param("userId") Long userId,
                                              @Param("commentId") Long commentId,
                                              @Param("isLiked") boolean isLiked) {
        return commentLikeService.saveOrUpdateCommentLikeDislike(userId, commentId, isLiked);
    }

    @PostMapping("/delete-like")
    public ResponseEntity<String> deleteLike(@Param("userId") Long userId,
                                             @Param("commentId") Long commentId) {
        return commentLikeService.deleteLike(userId, commentId);
    }
}
