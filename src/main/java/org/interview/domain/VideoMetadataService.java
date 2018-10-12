package org.interview.domain;

import org.interview.dao.VideoMetadataDAO;
import org.interview.domain.model.Genre;
import org.interview.domain.model.VideoMetadata;
import org.interview.repository.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoMetadataService {

    private final VideoMetadataRepository videoMetadataRepository;

    @Autowired
    public VideoMetadataService(VideoMetadataRepository videoMetadataRepository) {
        this.videoMetadataRepository = videoMetadataRepository;
    }

    public Iterable<VideoMetadata> findAll() {
        return videoMetadataRepository.findAll();
    }

    public void createVideo(VideoMetadata videoMetadata) {
        return videoMetadataRepository.save();
    }

    public void updateVideo(VideoMetadata videoMetadata) {
        return videoMetadataRepository.save();
    }

    public void deleteVideo(Long id) {
        videoMetadataRepository.deleteById(id);
    }

    public List<VideoMetadata> findByGenre(Genre genre) {

    }

    public List<VideoMetadata> findBySubgenre(String subgenre) {

    }
}
