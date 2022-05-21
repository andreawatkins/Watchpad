package com.watchpad.watchpadbackend.MediaRating;

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
public interface MediaRatingRepository extends JpaRepository<Rating, Long> {

    @Modifying
    @Query("UPDATE MediaRating r SET r.rating = :rating WHERE r.id = :ratingId")
    void setRating(@Param("rating") Float rating, @Param("ratingId") RatingKey ratingId);

    @Modifying
    void deleteById(@Param("ratingId") RatingKey ratingId);

    @Query("SELECT AVG(r.rating) FROM MediaRating r WHERE r.id.ratableEntityId = ?1")
    Optional<Float> getAverageRatingForRatableEntityId(@Param("entityId") Long entityId);

    @Query("SELECT COUNT(r) FROM MediaRating r WHERE r.id.ratableEntityId = ?1")
    long getCountOfRatingsByEntityId(@Param("entityId") Long entityId);

    @Query("SELECT r FROM MediaRating r WHERE r.id.userId = ?1 AND r.id.ratableEntityId = ?2")
    Optional<MediaRating> getMediaRatingByUserIdAndMediaId (Long userId, Long ratableEntityId);

    @Query("SELECT r FROM MediaRating r WHERE r.id.userId = ?1")
    Optional<List<MediaRating>> getAllMediaRatingsByUserId(Long userId);

    @Query("SELECT r FROM MediaRating r WHERE r.id.ratableEntityId = ?1")
    Optional<List<MediaRating>> getAllMediaRatingsByRatableEntityId(Long ratableEntityId);


}