package org.interview.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video/metadata")
public class VideoMetadataRestController {

    @RequestMapping(method = RequestMethod.POST)
    public VideoMetadataDTO create(VideoMetadataDTO videoMetadataDTO) {
        return new VideoMetadataDTO();
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public VideoMetadataDTO update(VideoMetadataDTO videoMetadataDTO) {
        return new VideoMetadataDTO();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") int itemId) {
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public boolean findAll() {
        return true;
    }
}
