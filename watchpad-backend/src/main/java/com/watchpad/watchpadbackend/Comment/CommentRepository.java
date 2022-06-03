package com.watchpad.watchpadbackend.Comment;

import com.watchpad.watchpadbackend.CommentLike.CommentLikeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  // @Query("SELECT c FROM Comment c WHERE c.media = ?1")
  // Optional<List<Comment>> findByMedia(Long mediaId);

  @Query("SELECT c FROM Comment c WHERE c.media.id = ?1")
  Optional<List<Comment>> findByMedia(Long mediaId);

  @Query("SELECT c FROM Comment c WHERE c.media.id = ?1 AND c.review = false")
  Optional<List<Comment>> findByTimestamp(Long mediaId);

  @Query(value = "SELECT media_id, count(comment_id) FROM Comments GROUP BY media_id ORDER BY Count(comment_id) DESC FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
  Optional<List<Long>> findMostCommentedMedia();

  @Query(value = "SELECT cl.comment_id, c.comment_timestamp, c.content, c.duration_timestamp, c.review, c.spoiler, c.media_id, c.user_id, cl.is_liked, COUNT(cl.is_liked)"
      + " FROM Comment_Like cl RIGHT JOIN comments c ON c.comment_id = cl.comment_id WHERE c.review = FALSE AND c. media_id = ?1"
      + " GROUP BY cl.comment_id, c.comment_timestamp, c.content, c.duration_timestamp, c.review, c.spoiler, c.media_id, c.user_id, cl.is_liked"
      + " ORDER BY cl.is_liked DESC, COUNT(is_liked) DESC", nativeQuery = true)
  Optional<List<Comment>> findMostLikedComments(Long mediaId);

  @Query("SELECT c FROM Comment c WHERE c.media.id = ?1 AND c.review = false")
  Optional<List<Comment>> findDurationCommentsByMedia(Long mediaId);

  @Modifying
  @Query("UPDATE Comment c SET c.score = :score WHERE c.comment_id = :commentId")
  void updateCommentScore(Long score, Long commentId);

}