package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchListRepository extends JpaRepository<WatchListEntry, Long> {

    //Optional<List<WatchListEntry>> findByMedia(Media media);

    Optional<List<WatchListEntry>> findByUser(User user);

    Optional<WatchListEntry> findByUserAndMedia(User user, Media media);

}
