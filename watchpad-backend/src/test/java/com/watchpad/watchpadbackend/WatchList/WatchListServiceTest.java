package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WatchListServiceTest {
    @Mock
    private WatchListRepository watchListRepository;
    private WatchListService watchListService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() { watchListService = new WatchListService(watchListRepository); }

    /*
    @Test
    void addToWatchList(){
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        Media media = new Media(123456L);
        Long id = 123456L;
        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        ArgumentCaptor<WatchListEntry> watchListEntryArgumentCaptor = ArgumentCaptor.forClass(WatchListEntry.class);
        Mockito.verify(watchListRepository).save(watchListEntryArgumentCaptor.capture());

        WatchListEntry capturedWatchListEntry = watchListEntryArgumentCaptor.getValue();
        assertEquals(capturedWatchListEntry, watchListEntry);
    }

     */
}
