package com.watchpad.watchpadbackend.CommentLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    @Modifying
    @Query("UPDATE CommentLike l SET l.isLiked = :isLiked WHERE l.id = :commentLikeId")
    void setIsLiked(Boolean isLiked, CommentLikeKey commentLikeId);

    @Modifying
    @Query("DELETE FROM CommentLike WHERE id.userId = ?1 AND id.commentId = ?2")
    void deleteById(Long userId, Long commentId);

    @Query("SELECT COUNT(l) FROM CommentLike l WHERE l.id.commentId = ?1 AND l.isLiked = TRUE")
    long getCountOfLikesByCommentId(Long entityId);

    @Query("SELECT COUNT(l) FROM CommentLike l WHERE l.id.commentId = ?1 AND l.isLiked = FALSE")
    long getCountOfDislikesByCommentId(Long commentId);

    @Query("SELECT l FROM CommentLike l WHERE l.id.userId = ?1 AND l.id.commentId = ?2")
    Optional<CommentLike> getCommentLikeByUserIdAndCommentId (Long userId, Long commentId);

}
