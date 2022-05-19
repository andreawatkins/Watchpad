package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.MediaRepository;
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
            Optional<WatchListEntry> queriedMedia = watchListRepository.findByUserAndMedia(watchListEntry.getUser(), watchListEntry.getMedia());
            if (!queriedMedia.isPresent()) {
                return new ResponseEntity("Media not in watchlist", HttpStatus.CONFLICT);
            }
            watchListRepository.delete(queriedMedia.get());
            return new ResponseEntity("Media removed from watchlist", HttpStatus.OK);

    }

    public ResponseEntity removeEntry(WatchListEntry watchListEntry) {
        Optional<WatchListEntry> entry = watchListRepository.findByMediaId(watchListEntry.getMedia().getId());
        if (!entry.isPresent()) {
            return new ResponseEntity("Media not in watchlist", HttpStatus.CONFLICT);
        }
        watchListRepository.delete(entry.get());
        return new ResponseEntity("Media removed from watchlist", HttpStatus.OK);
    }

    public ResponseEntity<Optional<List<WatchListEntry>>> getWatchList(Long userId) {
        return new ResponseEntity(watchListRepository.findByUserId(userId), HttpStatus.OK);
    }

}
