package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MediaRatingRepository extends RatingRepository {

    @Query("SELECT r FROM MediaRating r WHERE r.media.externalId = ?1")
    List<Rating> findAllByMediaId(Long mediaExternalId);

    @Query("SELECT r FROM MediaRating r WHERE r.user.id = ?1 AND r.media.id = ?2")
    Optional<Rating> findRatingByUserAndMediaId(Long userId, Long mediaExternalId);

    @Query("SELECT r FROM MediaRating r WHERE r.media.externalId = ?1")
    List<Rating> findAllByMediaExternalId(String mediaExternalId);

    @Query("SELECT r FROM MediaRating r WHERE r.user.id = ?1 AND r.media.externalId = ?2")
    Optional<Rating> findRatingByUserAndMediaExternalId(Long userId, String mediaExternalId);

}