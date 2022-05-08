package com.watchpad.watchpadbackend.Media;


import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/media")
public class MediaController {
    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/save-media")
    public void registerMedia(@RequestBody Media media) {
        mediaService.registerMedia(media);
    }

    @GetMapping("/get-media")
    public ResponseEntity<List<Media>> getMedia(){
        return mediaService.getAllMedia();
    }

    @GetMapping("/get-media-by-id")
    public ResponseEntity<Media> getMediaById(@PathVariable("id") Long id){
        return mediaService.getMediaById(id);
    }

    @GetMapping("/get-media-by-externalId")
    public ResponseEntity<Media> getMediaByExternalId(@PathVariable("externalId") String externalId){
        return mediaService.getMediaByExternalId(externalId);
    }


}
