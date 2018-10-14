package org.interview.domain;

import org.interview.dao.GenreDAO;
import org.interview.dao.SubgenreDAO;
import org.interview.dao.VideoMetadataDAO;
import org.interview.domain.model.Genre;
import org.interview.domain.model.VideoMetadata;
import org.interview.repository.GenreRepository;
import org.interview.repository.SubgenreRepository;
import org.interview.repository.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.interview.utils.Util.setIfPresentInteger;
import static org.interview.utils.Util.setIfPresentString;

@Service
public class VideoMetadataService {

    private final VideoMetadataRepository videoMetadataRepository;
    private final SubgenreRepository subgenreRepository;
    private final ValidationService validationService;

    private final Map<String, GenreDAO> genresCache;

    @Autowired
    public VideoMetadataService(VideoMetadataRepository videoMetadataRepository, GenreRepository genreRepository, SubgenreRepository subgenreRepository,  ValidationService validationService) {
        this.videoMetadataRepository = videoMetadataRepository;
        this.subgenreRepository = subgenreRepository;
        this.validationService = validationService;

        genresCache = genreRepository.findAll().stream()
                .collect(Collectors.toMap(GenreDAO::getName, Function.identity()));
    }

    @Transactional
    public VideoMetadata createVideo(VideoMetadata videoMetadata) {
        validationService.checkIfCreationParametersValid(videoMetadata);
        VideoMetadataDAO dao = domainToDao(videoMetadata);
        VideoMetadataDAO saved = videoMetadataRepository.save(dao);
        return videoMetadataDaoToDomain(saved);
    }

    @Transactional
    public VideoMetadata updateVideo(VideoMetadata videoMetadata) {
        validationService.checkIfUpdateParametersValid(videoMetadata);
        // this video exists because checkIfUpdateParametersValid() didn't throw an exception
        VideoMetadataDAO existingVideoMetadata = videoMetadataRepository.findById(videoMetadata.getId()).get();
        VideoMetadataDAO dao = buildUpdatedVideoMetadataDao(videoMetadata, existingVideoMetadata);
        return videoMetadataDaoToDomain(videoMetadataRepository.save(dao));
    }

    @Transactional
    public void deleteVideo(Long id) {
        validationService.checkIfDeleteParametersValid(id);
        videoMetadataRepository.deleteById(id);
    }

    public List<VideoMetadata> findAll() {
        Iterable<VideoMetadataDAO> videoMetadataDaos = videoMetadataRepository.findAll();
        List<VideoMetadata> videoMetadataList = new ArrayList<>();
        for (VideoMetadataDAO videoMetadataDao : videoMetadataDaos) {
            videoMetadataList.add(videoMetadataDaoToDomain(videoMetadataDao));
        }
        return videoMetadataList;
    }

    public List<VideoMetadata> findByGenre(Genre genre) {
        List<VideoMetadataDAO> daos = videoMetadataRepository.findVideoMetadataDAOByGenre_Name(genre.getName());
        return daos.stream().map(this::videoMetadataDaoToDomain).collect(Collectors.toList());
    }

    public List<VideoMetadata> findBySubgenre(String subgenre) {
        List<VideoMetadataDAO> daos = videoMetadataRepository.findVideoMetadataDAOBySubgenres_Name(subgenre);
        return daos.stream().map(this::videoMetadataDaoToDomain).collect(Collectors.toList());
    }

    private VideoMetadataDAO buildUpdatedVideoMetadataDao(VideoMetadata videoMetadata, VideoMetadataDAO existingVideoMetadata) {
        if (videoMetadata.getAlbum() != null) {
            existingVideoMetadata.setAlbum(videoMetadata.getAlbum().isPresent() ? videoMetadata.getAlbum().get() : null);
        }
        setIfPresentString(videoMetadata::getTitle, existingVideoMetadata::setTitle);
        setIfPresentString(videoMetadata::getArtist, existingVideoMetadata::setArtist);
        setIfPresentInteger(videoMetadata::getDuration, existingVideoMetadata::setDuration);
        setIfPresentInteger(videoMetadata::getReleaseYear, existingVideoMetadata::setReleaseYear);

        if (videoMetadata.getGenre() != null) {
            existingVideoMetadata.setGenre(genresCache.get(videoMetadata.getGenre().getName()));
        }

        if (videoMetadata.getSubgenres() != null) {
            List<SubgenreDAO> subgenres = getSubgenreDAOList(videoMetadata.getSubgenres());
            existingVideoMetadata.setSubgenres(subgenres);
        }
        return existingVideoMetadata;
    }


    private List<SubgenreDAO> getSubgenreDAOList(List<String> subgenres) {
        List<SubgenreDAO> subgenreDaos = subgenreRepository.findByNameIn(subgenres);
        for (String subgenre : subgenres) {
            if (subgenreDaos.stream().noneMatch(s -> s.getName().equals(subgenre))) {
                SubgenreDAO subgenreDAO = new SubgenreDAO();
                subgenreDAO.setName(subgenre);
                subgenreDaos.add(subgenreDAO);
            }
        }
        return subgenreDaos;
    }

    private VideoMetadata videoMetadataDaoToDomain(VideoMetadataDAO videoMetadataDAO) {
        return VideoMetadata.builder()
                .setId(videoMetadataDAO.getId())
                .setTitle(videoMetadataDAO.getTitle())
                .setAlbum(Optional.ofNullable(videoMetadataDAO.getAlbum()))
                .setArtist(videoMetadataDAO.getArtist())
                .setGenre(Genre.fromName(videoMetadataDAO.getGenre().getName()))
                .setSubgenres(videoMetadataDAO.getSubgenres().stream()
                        .map(SubgenreDAO::getName).collect(Collectors.toList()))
                .setReleaseYear(videoMetadataDAO.getReleaseYear())
                .setDuration(videoMetadataDAO.getDuration())
                .build();
    }

    private VideoMetadataDAO domainToDao(VideoMetadata videoMetadata) {
        VideoMetadataDAO dao = new VideoMetadataDAO();
        dao.setId(videoMetadata.getId());
        dao.setTitle(videoMetadata.getTitle());
        dao.setArtist(videoMetadata.getArtist());
        dao.setGenre(genresCache.get(videoMetadata.getGenre().getName()));
        dao.setDuration(videoMetadata.getDuration());
        if (videoMetadata.getAlbum() != null) {
            dao.setAlbum(videoMetadata.getAlbum().isPresent() ? videoMetadata.getAlbum().get() : null);
        }
        dao.setReleaseYear(videoMetadata.getReleaseYear());
        List<SubgenreDAO> subgenres = getSubgenreDAOList(videoMetadata.getSubgenres());
        dao.setSubgenres(subgenres);
        return dao;
    }
}
