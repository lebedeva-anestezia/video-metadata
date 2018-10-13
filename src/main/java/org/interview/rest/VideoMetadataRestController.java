package org.interview.rest;

import org.interview.domain.VideoMetadataService;
import org.interview.domain.model.Genre;
import org.interview.domain.model.VideoMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/video/metadata")
public class VideoMetadataRestController {

    private final VideoMetadataService videoMetadataService;

    @Autowired
    public VideoMetadataRestController(VideoMetadataService videoMetadataService) {
        this.videoMetadataService = videoMetadataService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public VideoMetadataDTO create(@RequestBody VideoMetadataDTO videoMetadataDTO) {
        VideoMetadata video = videoMetadataService.createVideo(DTOToDomainConverter.videoMetadata(videoMetadataDTO));
        return DomainToDTOConverter.videoMetadataToDTO(video);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public VideoMetadataDTO update(@RequestBody VideoMetadataDTO videoMetadataDTO) {
        VideoMetadata video = videoMetadataService.updateVideo(DTOToDomainConverter.videoMetadata(videoMetadataDTO));
        return DomainToDTOConverter.videoMetadataToDTO(video);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") Long itemId) {
        videoMetadataService.deleteVideo(itemId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<VideoMetadataDTO> findAll() {
        return videoMetadataService.findAll().stream()
                .map(DomainToDTOConverter::videoMetadataToDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/genre", method = RequestMethod.GET)
    public List<VideoMetadataDTO> findAllByGenre(@RequestParam("name") String genre) {
        return videoMetadataService.findByGenre(Genre.fromName(genre)).stream()
                .map(DomainToDTOConverter::videoMetadataToDTO)
                .collect(Collectors.toList());
    }


    @RequestMapping(value="/subgenre", method = RequestMethod.GET)
    public List<VideoMetadataDTO> findAllBySubgenre(@RequestParam("name") String subgenre) {
        return videoMetadataService.findBySubgenre(subgenre).stream()
                .map(DomainToDTOConverter::videoMetadataToDTO)
                .collect(Collectors.toList());
    }
}
