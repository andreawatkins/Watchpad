package com.watchpad.watchpadbackend.Media;


import com.watchpad.watchpadbackend.Rating.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        mediaService.reigsterMedia(media);
    }



}
