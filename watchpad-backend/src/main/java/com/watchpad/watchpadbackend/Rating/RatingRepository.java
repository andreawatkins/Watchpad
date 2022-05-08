package com.watchpad.watchpadbackend.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.id = ?1")
    Optional<Rating> findByRatingId(Long id);

    @Query("SELECT r FROM Rating r WHERE r.user.id = ?1")
    List<Rating> findAllByUserId(Long userId);

    @Query("SELECT r FROM Rating r WHERE r.ratableEntity.id = ?1")
    List<Rating> findAllByRatableEntityId(Long ratableEntityId);

    @Query("SELECT r FROM Rating r WHERE r.user.id = ?1 AND r.ratableEntity.id = ?2")
    Optional<Rating> findRatingByUserAndRatableEntity(Long userId, Long ratableEntityId);


}
