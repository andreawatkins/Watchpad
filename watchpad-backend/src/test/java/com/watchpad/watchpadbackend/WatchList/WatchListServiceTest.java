package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchListServiceTest {
    @Mock
    private WatchListRepository watchListRepository;
    private WatchListService watchListService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MediaRepository mediaRepository;


    @BeforeEach
    void setUp() { watchListService = new WatchListService(watchListRepository); }

    @Test
    void getWatchList(){
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        watchListService.getWatchList(user.getId());
        Mockito.verify(watchListRepository).findByUserId(user.getId());

    }

    @Test
    void addToWatchList(){
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        Media media = new Media(123123L);
        Long id = 123456L;
        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        watchListService.addToWatchList(watchListEntry);
        ArgumentCaptor<WatchListEntry> watchListEntryArgumentCaptor = ArgumentCaptor.forClass(WatchListEntry.class);
        Mockito.verify(watchListRepository).save(watchListEntryArgumentCaptor.capture());

        WatchListEntry capturedWatchListEntry = watchListEntryArgumentCaptor.getValue();
        assertEquals(watchListEntry, capturedWatchListEntry);
        ResponseEntity<WatchListEntry> actual = watchListService.addToWatchList(watchListEntry);
        ResponseEntity<String> expected = new ResponseEntity<>("Media added to watchlist!", HttpStatus.OK);
        assertEquals(expected, actual);

    }

    @Test
    void mediaIsAlreadyInWatchlist() {
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        Media media = new Media(123123L);
        Long id = 123456L;
        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        when(watchListRepository.findByUserAndMedia(user, media)).thenReturn(Optional.of(watchListEntry));

        ResponseEntity<String> actual = watchListService.addToWatchList(watchListEntry);
        ResponseEntity<String> expected = new ResponseEntity("Media already in watchlist", HttpStatus.CONFLICT);
        assertEquals(actual, expected);

    }

    @Test
    void removeFromWatchlist() {
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        Media media = new Media(123123L);
        Long id = 123456L;
        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        when(watchListRepository.findByUserAndMedia(user, media)).thenReturn(Optional.of(watchListEntry));

        ResponseEntity<String> actual = watchListService.removeFromWatchList(watchListEntry);
        ResponseEntity<String> expected = new ResponseEntity<>("Media removed from watchlist", HttpStatus.OK);
        assertEquals(expected, actual);



    }


}
