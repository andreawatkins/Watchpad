package com.watchpad.watchpadbackend.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  // @Query("SELECT c FROM Comment c WHERE c.media = ?1")
  // Optional<List<Comment>> findByMedia(Long mediaId);

  @Query("SELECT c FROM Comment c WHERE c.media.id = ?1")
  Optional<List<Comment>> findByMedia(Long mediaId);

  @Query("SELECT c FROM Comment c WHERE c.media.id = ?1 AND c.review = false")
  Optional<List<Comment>> findByTimestamp(Long mediaId);

}