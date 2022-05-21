package com.watchpad.watchpadbackend.MediaLike;

import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaLikeRepository extends JpaRepository<Rating, Long> {

    @Modifying
    @Query("UPDATE MediaLike l SET l.isLiked = :isLiked WHERE l.id = :ratingId")
    void setIsLiked(@Param("isLiked") Boolean isLiked, @Param("ratingId") RatingKey ratingId);

    @Modifying
    void deleteById(@Param("ratingId") RatingKey ratingId);

    @Query("SELECT COUNT(l) FROM MediaLike l WHERE l.id.ratableEntityId = ?1 AND l.isLiked = TRUE")
    long getCountOfLikesByEntityId(@Param("entityId") Long entityId);

    @Query("SELECT COUNT(l) FROM MediaLike l WHERE l.id.ratableEntityId = ?1 AND l.isLiked = FALSE")
    long getCountOfDislikesByEntityId(@Param("entityId") Long entityId);

    @Query("SELECT l FROM MediaLike l WHERE l.id.userId = ?1 AND l.id.ratableEntityId = ?2")
    Optional<MediaLike> getMediaLikeByUserIdAndMediaId (Long userId, Long ratableEntityId);

    @Query("SELECT l FROM MediaLike l WHERE l.id.userId = ?1")
    Optional<List<MediaLike>> getAllMediaLikesByUserId(Long userId);

    @Query("SELECT l FROM MediaLike l WHERE l.id.ratableEntityId = ?1")
    Optional<List<MediaLike>> getAllMediaLikesByRatableEntityId(Long ratableEntityId);

}
