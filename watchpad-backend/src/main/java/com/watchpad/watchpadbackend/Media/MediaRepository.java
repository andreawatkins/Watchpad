package com.watchpad.watchpadbackend.Media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM Media m WHERE m.id = ?1")
    Optional<Media> findById(Long id);

    @Query("SELECT m FROM Media m WHERE m.externalId = ?1")
    Optional<Media> findByExternalId(String id);

    @Query("SELECT m FROM Media m WHERE m.id = ?1 OR m.externalId = ?2")
    Optional<Media> findMediaByIdOrExternalId(Long id, String externalId);


}
