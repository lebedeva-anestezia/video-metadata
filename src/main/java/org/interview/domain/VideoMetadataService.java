package org.interview.domain;

import org.interview.dao.VideoMetadataDAO;
import org.interview.domain.model.Genre;
import org.interview.domain.model.VideoMetadata;
import org.interview.repository.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoMetadataService {

    private final VideoMetadataRepository videoMetadataRepository;
    private final ValidationService validationService;

    @Autowired
    public VideoMetadataService(VideoMetadataRepository videoMetadataRepository, ValidationService validationService) {
        this.videoMetadataRepository = videoMetadataRepository;
        this.validationService = validationService;
    }

    public List<VideoMetadata> findAll() {
        Iterable<VideoMetadataDAO> videoMetadataDaos = videoMetadataRepository.findAll();
        List<VideoMetadata> videoMetadataList = new ArrayList<>();
        for (VideoMetadataDAO videoMetadataDao : videoMetadataDaos) {
            videoMetadataList.add(daoToDomain(videoMetadataDao));
        }
        return videoMetadataList;
    }

    public void createVideo(VideoMetadata videoMetadata) {
        videoMetadataRepository.save(domainToDao(videoMetadata));
    }

    public void updateVideo(VideoMetadata videoMetadata) {
        videoMetadataRepository.save(domainToDao(videoMetadata));
    }

    public void deleteVideo(Long id) {
        videoMetadataRepository.deleteById(id);
    }

    public List<VideoMetadata> findByGenre(Genre genre) {
       // List<VideoMetadataDAO> daos = videoMetadataRepository.findByGenre(genre.name());
      //  return daos.stream().map(VideoMetadataService::daoToDomain).collect(Collectors.toList());
        return null;
    }

    public List<VideoMetadata> findBySubgenre(String subgenre) {
        return null;
    }

    static VideoMetadata daoToDomain(VideoMetadataDAO videoMetadataDAO) {
        return VideoMetadata.builder().build();
    }

    static VideoMetadataDAO domainToDao(VideoMetadata videoMetadata) {
        VideoMetadataDAO dao = new VideoMetadataDAO();
        return dao;
    }
}
