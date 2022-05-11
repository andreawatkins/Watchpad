package com.watchpad.watchpadbackend.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RatingRepository extends JpaRepository<Rating, Long> {
//
//    @Query("SELECT r FROM Rating r WHERE r.id = ?1")
//    Optional<Rating> findByRatingId(Long id);

    @Query("SELECT r FROM Rating r WHERE r.user.id = ?1")
    List<Rating> findAllByUserId(Long userId);

}
