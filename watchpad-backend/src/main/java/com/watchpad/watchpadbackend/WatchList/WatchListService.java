package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public WatchListService(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    public ResponseEntity addToWatchList(WatchListEntry watchListEntry) {
            Optional<WatchListEntry> queriedMedia = watchListRepository.findByUserAndMedia(watchListEntry.getUser(), watchListEntry.getMedia());
            if (queriedMedia.isPresent() ) {
                return new ResponseEntity("Media already in watchlist", HttpStatus.CONFLICT);
            }

            watchListRepository.save(watchListEntry);
            return new ResponseEntity("Media added to watchlist!", HttpStatus.OK);

    }

    public ResponseEntity<String> removeFromWatchList(WatchListEntry watchListEntry) {
        try {
            Optional<WatchListEntry> queriedMedia = watchListRepository.findByUserAndMedia(watchListEntry.getUser(), watchListEntry.getMedia());
            if (!queriedMedia.isPresent()) {
                return new ResponseEntity("Media not in watchlist", HttpStatus.CONFLICT);
            }
            watchListRepository.deleteAllById(Collections.singleton(queriedMedia.get().getId()));
            return new ResponseEntity("Media removed from watchlist", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<List<WatchListEntry>>> getWatchList(User user) {
        return new ResponseEntity(watchListRepository.findByUser(user), HttpStatus.OK);
    }

}
