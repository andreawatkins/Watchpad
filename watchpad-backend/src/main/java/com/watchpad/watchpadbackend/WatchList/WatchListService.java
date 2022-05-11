package com.watchpad.watchpadbackend.WatchList;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchListService {

    private WatchListRepository watchListRepository;


    @Autowired
    public WatchListService(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    public ResponseEntity<String> addToWatchList(WatchListEntry watchListEntry) {
        try {
            Optional<WatchListEntry> queriedMedia = watchListRepository.findById(watchListEntry.getMedia().getId());
            if (queriedMedia.isPresent()) {
                return new ResponseEntity("Media already in watchlist", HttpStatus.CONFLICT);
            }
            watchListRepository.save(watchListEntry);
            return new ResponseEntity("Media added to watchlist", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> removeFromWatchList(WatchListEntry watchListEntry) {
        try {
            Optional<WatchListEntry> queriedMedia = watchListRepository.findById(watchListEntry.getMedia().getId());
            if (!queriedMedia.isPresent()) {
                return new ResponseEntity("Media not in watchlist", HttpStatus.BAD_REQUEST);
            }
            watchListRepository.delete(watchListEntry);
            return new ResponseEntity("Media removed from watchlist", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<WatchListEntry>> getWatchList() {
        return new ResponseEntity(watchListRepository.findAll(), HttpStatus.OK);
    }

}
