package com.watchpad.watchpadbackend.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.watchpad.watchpadbackend.User.*;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

   // @Query("SELECT c FROM Comment c WHERE c.media_id = ?1")
    //Optional<Comment> findByMediaId(Long id);

}