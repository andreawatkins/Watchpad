package com.watchpad.watchpadbackend.WatchList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListService {

    private WatchListRepository watchListRepository;


    @Autowired
    public WatchListService(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }
    public void addToWatchList(WatchListEntry watchListEntry) {
        watchListRepository.save(watchListEntry);
    }

    public void removeFromWatchList(WatchListEntry watchListEntry) {
        watchListRepository.delete(watchListEntry);
    }

    public List<WatchListEntry> getWatchList() {
        return watchListRepository.findAll();
    }

}
