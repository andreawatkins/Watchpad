package com.watchpad.watchpadbackend.WatchList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchListRepository extends JpaRepository<WatchListEntry, Long> {


}
