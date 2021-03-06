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

  @Query(value = "SELECT media_id, count(comment_id) FROM comment GROUP BY media_id ORDER BY Count(comment_id) DESC FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
  Optional<List<Long>> findMostCommentedMedia();

  @Query(value = "SELECT * FROM comment WHERE media_id = ?1 AND review = true ORDER BY score DESC", nativeQuery = true)
  Optional<List<Comment>> findMostLikedReviews(Long mediaId);

  @Query("SELECT c FROM Comment c WHERE c.media.id = ?1 AND c.review = true")
  Optional<List<Comment>> findReviewsByMedia(Long mediaId);

  @Query(value = "SELECT * FROM comment WHERE media_id = ?1 AND review = false ORDER BY duration_timestamp DESC", nativeQuery = true)
  Optional<List<Comment>> findDurationCommentsByMediaSorted(Long mediaId);

  @Query(value = "SELECT c.* FROM Comment c JOIN (SELECT f.followee_username, u.id FROM Follow f " +
  "LEFT JOIN users u on u.username = f.followee_username "+
  "WHERE f.follower_username = ?1) AS a "+
  "ON c.user_id = a.id WHERE media_ID = ?2 AND review = true;", nativeQuery = true)
  Optional<List<Comment>> findReviewsByFollower(String username, Long mediaId);

  @Modifying
  @Query("UPDATE Comment c SET c.score = :score WHERE c.comment_id = :commentId")
  void updateCommentScore(Long score, Long commentId);

}