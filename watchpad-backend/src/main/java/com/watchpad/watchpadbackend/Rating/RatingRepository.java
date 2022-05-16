package com.watchpad.watchpadbackend.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.id.userId = ?1")
    Optional<List<Rating>> findAllByUserId(Long userId);

    @Query("SELECT r FROM Rating r WHERE r.id.ratableEntityId = ?1")
    Optional<List<Rating>> findAllByRatableEntityId(Long ratableEntityId);

    @Query("SELECT r FROM Rating r WHERE r.id.userId = ?1 AND r.id.ratableEntityId = ?2")
    Optional<Rating> findByUserIdAndRatableEntityId  (Long userId, Long ratableEntityId);

    @Modifying
    @Query("UPDATE Rating r SET r.isLiked = :isLiked WHERE r.id = :ratingId")
    void setIsLiked(@Param("isLiked") boolean isLiked, @Param("ratingId") RatingKey ratingId);

    @Modifying
    void deleteById(@Param("ratingId") RatingKey ratingId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.id.ratableEntityId = ?1 AND r.isLiked = TRUE")
    long getCountOfLikesByEntityId(@Param("entityId") Long entityId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.id.ratableEntityId = ?1 AND r.isLiked = FALSE")
    long getCountOfDislikesByEntityId(@Param("entityId") Long entityId);

}
