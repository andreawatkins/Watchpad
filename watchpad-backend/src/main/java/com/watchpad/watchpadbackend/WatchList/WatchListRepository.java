package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchListRepository extends JpaRepository<WatchListEntry, Long> {

    //Optional<List<WatchListEntry>> findByMedia(Media media);

    @Query("SELECT w.media.id FROM WatchListEntry w WHERE w.user.id = ?1")
    Optional<List<Long>> findAllMediaInWatchlist(Long userId);

    @Query("SELECT w FROM WatchListEntry w WHERE w.user.id = ?1")
    Optional<List<WatchListEntry>> findByUserId(Long userId);

    @Query("SELECT w FROM WatchListEntry w WHERE w.user = ?1 AND w.media = ?2")
    Optional<WatchListEntry> findByUserAndMedia(User user, Media media);

    @Query("SELECT w FROM WatchListEntry w WHERE w.media.id = ?1")
    Optional<WatchListEntry> findByMediaId(Long mediaId);

}
