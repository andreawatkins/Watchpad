package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

    private final WatchListService watchListService;

    @Autowired
    public WatchListController(WatchListService watchListService) {
        this.watchListService = watchListService;
    }

    @PostMapping("/add-to-watchlist")
    public ResponseEntity<String> addToWatchList(@RequestBody WatchListEntry watchListEntry) {
        return watchListService.addToWatchList(watchListEntry);
    }
    //return response entity instead of void.

    @DeleteMapping("/remove-from-watchlist")
    public ResponseEntity<String> removeFromWatchList(@RequestBody WatchListEntry watchListEntry) {
        return watchListService.removeFromWatchList(watchListEntry);
    }
    //send response entity instead of void

    @GetMapping("/get-watchlist")
    public ResponseEntity<Optional<List<WatchListEntry>>> getWatchList(@RequestBody Media media) {

        return watchListService.getWatchList(media);
    }

}


