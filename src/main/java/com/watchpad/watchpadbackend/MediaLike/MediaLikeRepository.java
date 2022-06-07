package com.watchpad.watchpadbackend.MediaLike;

import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaLikeRepository extends JpaRepository<Rating, Long> {

    @Modifying
    @Query("UPDATE MediaLike l SET l.isLiked = :isLiked WHERE l.id = :ratingId")
    void setIsLiked(Boolean isLiked, RatingKey ratingId);

    @Modifying
    @Query("DELETE FROM MediaLike WHERE id.userId = ?1 AND id.ratableEntityId = ?2")
    void deleteById(Long userId, Long ratableEntityId);

    @Query("SELECT COUNT(l) FROM MediaLike l WHERE l.id.ratableEntityId = ?1 AND l.isLiked = TRUE")
    long getCountOfLikesByEntityId(Long entityId);

    @Query("SELECT COUNT(l) FROM MediaLike l WHERE l.id.ratableEntityId = ?1 AND l.isLiked = FALSE")
    long getCountOfDislikesByEntityId(Long entityId);

    @Query("SELECT l FROM MediaLike l WHERE l.id.userId = ?1 AND l.id.ratableEntityId = ?2")
    Optional<MediaLike> getMediaLikeByUserIdAndMediaId (Long userId, Long ratableEntityId);

    @Query("SELECT l FROM MediaLike l WHERE l.id.userId = ?1")
    Optional<List<MediaLike>> getAllMediaLikesByUserId(Long userId);

    @Query("SELECT l FROM MediaLike l WHERE l.id.ratableEntityId = ?1")
    Optional<List<MediaLike>> getAllMediaLikesByRatableEntityId(Long ratableEntityId);

}
