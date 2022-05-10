package com.watchpad.watchpadbackend.WatchList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WatchListController {

    private final WatchListService watchListService;

    @Autowired
    public WatchListController(WatchListService watchListService) {
        this.watchListService = watchListService;
    }

    @PostMapping("/add-to-watchlist")
    public void addToWatchList(WatchListEntry watchListEntry) {
        watchListService.addToWatchList(watchListEntry);
    }
    //return response entity instead of void.

    @DeleteMapping("/remove-from-watchlist")
    public void removeFromWatchList(WatchListEntry watchListEntry) {
        watchListService.removeFromWatchList(watchListEntry);
    }
    //send response entity instead of void

    @GetMapping("/get-watchlist")
    public List<WatchListEntry> getWatchList() {
        return watchListService.getWatchList();
    }

}


