package com.watchpad.watchpadbackend.Media;

import com.watchpad.watchpadbackend.Rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT r FROM Rating r WHERE r.id = ?1")
    Optional<Media> findByMediaId(Long id);

}
