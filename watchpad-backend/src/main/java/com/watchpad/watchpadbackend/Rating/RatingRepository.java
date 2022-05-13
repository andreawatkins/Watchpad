package com.watchpad.watchpadbackend.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

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
}
