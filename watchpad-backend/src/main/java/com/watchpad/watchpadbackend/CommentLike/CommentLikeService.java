package com.watchpad.watchpadbackend.CommentLike;

import com.watchpad.watchpadbackend.Comment.Comment;
import com.watchpad.watchpadbackend.Comment.CommentRepository;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentLikeService(CommentLikeRepository commentLikeRepository,
                            CommentRepository commentRepository,
                            UserRepository userRepository) {
        this.commentLikeRepository = commentLikeRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Long> countLikesForComment(Long commentId) {
        return new ResponseEntity<>(commentLikeRepository.getCountOfLikesByCommentId(commentId), HttpStatus.OK);
    }

    public ResponseEntity<Long> countDislikesForComment(Long commentId) {
        return new ResponseEntity<>(commentLikeRepository.getCountOfDislikesByCommentId(commentId), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> saveOrUpdateCommentLikeDislike(Long userId, Long commentId, boolean isLiked){
        try {
            //If user does not exist in user repo, return conflict
            Optional<User> userOptional = userRepository.findById(userId);
            if(!userOptional.isPresent()){
                return new ResponseEntity("User not found, comment like/dislike not saved!", HttpStatus.CONFLICT);
            }

            //if like/dislike already exists for Comment by User, update value
            Optional<CommentLike> commentLikeOptional = commentLikeRepository.getCommentLikeByUserIdAndCommentId(userId, commentId);
            if (commentLikeOptional.isPresent()) {
                commentLikeRepository.setIsLiked(isLiked, commentLikeOptional.get().getId());
                return new ResponseEntity("Existing comment like/dislike updated with new value!", HttpStatus.CREATED);
            }

            //If comment not in comment repo, return error
            Optional<Comment> commentOptional = commentRepository.findById(commentId);
            if(!commentOptional.isPresent()){
                return new ResponseEntity("Comment not found in repo, like/dislike not saved!", HttpStatus.CONFLICT);
            } else {
                CommentLike newCommentLike = new CommentLike(userOptional.get(), commentOptional.get(),isLiked);
                commentLikeRepository.save(newCommentLike);

                return new ResponseEntity("Comment like/dislike registered!", HttpStatus.CREATED);
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<CommentLike>> getLike(Long userId, Long commentId) {
        return new ResponseEntity<>(commentLikeRepository.getCommentLikeByUserIdAndCommentId(userId, commentId), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteLike(Long userId, Long commentId) {
        Optional<CommentLike> commentLikeOptional = commentLikeRepository.getCommentLikeByUserIdAndCommentId(userId, commentId);
        if (commentLikeOptional.isPresent()) {
            commentLikeRepository.deleteById(userId, commentId);
            return new ResponseEntity<>("Comment like deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No existing comment like to delete!", HttpStatus.CONFLICT);
        }
    }

}