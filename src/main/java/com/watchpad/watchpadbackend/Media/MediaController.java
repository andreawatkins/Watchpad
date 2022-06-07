package com.watchpad.watchpadbackend.Media;


import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/media")
public class MediaController {
    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/save-media")
    public ResponseEntity<String> registerMedia(@RequestBody Media media) {
        return mediaService.registerMedia(media);
    }

    @GetMapping("/get-media")
    public ResponseEntity<List<Media>> getMedia(){
        return mediaService.getAllMedia();
    }

    @GetMapping("/get-media-by-id")
    public ResponseEntity<Optional<Media>> getMediaById(@Param("id") Long id){
        return mediaService.getMediaById(id);
    }


}
