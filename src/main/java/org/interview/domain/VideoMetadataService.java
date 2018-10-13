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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class VideoMetadataService {

    private final VideoMetadataRepository videoMetadataRepository;
    private final GenreRepository genreRepository;
    private final SubgenreRepository subgenreRepository;
    private final ValidationService validationService;

    private final Map<String, GenreDAO> genresCache;

    @Autowired
    public VideoMetadataService(VideoMetadataRepository videoMetadataRepository, GenreRepository genreRepository, SubgenreRepository subgenreRepository,  ValidationService validationService) {
        this.videoMetadataRepository = videoMetadataRepository;
        this.genreRepository = genreRepository;
        this.subgenreRepository = subgenreRepository;
        this.validationService = validationService;

        genresCache = genreRepository.findAll().stream()
                .collect(Collectors.toMap(GenreDAO::getName, Function.identity()));
    }

    public List<VideoMetadata> findAll() {
        Iterable<VideoMetadataDAO> videoMetadataDaos = videoMetadataRepository.findAll();
        List<VideoMetadata> videoMetadataList = new ArrayList<>();
        for (VideoMetadataDAO videoMetadataDao : videoMetadataDaos) {
            videoMetadataList.add(videoMetadataDaoToDomain(videoMetadataDao));
        }
        return videoMetadataList;
    }

    public VideoMetadata createVideo(VideoMetadata videoMetadata) {
        validationService.isCreationParametersValid(videoMetadata);
        VideoMetadataDAO dao = domainToDao(videoMetadata);
        List<SubgenreDAO> subgenres = subgenreRepository.findByNameIn(videoMetadata.getSubgenres());
        for (String subgenre: videoMetadata.getSubgenres()) {
            if (subgenres.stream().noneMatch(s -> s.getName().equals(subgenre))) {
                SubgenreDAO subgenreDAO = new SubgenreDAO();
                subgenreDAO.setName(subgenre);
                subgenreDAO = subgenreRepository.save(subgenreDAO);
                subgenres.add(subgenreDAO);
            }
        }
        dao.setSubgenres(subgenres);
        VideoMetadataDAO saved = videoMetadataRepository.save(dao);
        return videoMetadataDaoToDomain(saved);
    }

    public VideoMetadata updateVideo(VideoMetadata videoMetadata) {
        validationService.isUpdateParametersValid(videoMetadata);
        Optional<VideoMetadataDAO> existingVideoMetadata = videoMetadataRepository.findById(videoMetadata.getId());
        if (!existingVideoMetadata.isPresent()) {
            throw new IllegalArgumentException("There is no such id");
        }
        VideoMetadataDAO dao = existingVideoMetadata.get();
        setIfPresentString(videoMetadata::getAlbum, dao::setAlbum);
        setIfPresentString(videoMetadata::getTitle, dao::setTitle);
        setIfPresentString(videoMetadata::getArtist, dao::setArtist);
        setIfPresentInteger(videoMetadata::getDuration, dao::setDuration);
        setIfPresentInteger(videoMetadata::getReleaseYear, dao::setReleaseYear);

        if (videoMetadata.getGenre() != null) {
            dao.setGenre(genresCache.get(videoMetadata.getGenre().getName()));
        }

        if (videoMetadata.getSubgenres() != null) {
            List<SubgenreDAO> subgenres = subgenreRepository.findByNameIn(videoMetadata.getSubgenres());
            for (String subgenre: videoMetadata.getSubgenres()) {
                if (subgenres.stream().noneMatch(s -> s.getName().equals(subgenre))) {
                    SubgenreDAO subgenreDAO = new SubgenreDAO();
                    subgenreDAO.setName(subgenre);
                    subgenreDAO = subgenreRepository.save(subgenreDAO);
                    subgenres.add(subgenreDAO);
                }
            }
            dao.setSubgenres(subgenres);
        }

        return videoMetadataDaoToDomain(videoMetadataRepository.save(dao));
    }

    public void deleteVideo(Long id) {
        videoMetadataRepository.deleteById(id);
    }

    public List<VideoMetadata> findByGenre(Genre genre) {
        List<VideoMetadataDAO> daos = videoMetadataRepository.findVideoMetadataDAOByGenre_Name(genre.getName());
        return daos.stream().map(VideoMetadataService::videoMetadataDaoToDomain).collect(Collectors.toList());
    }

    public List<VideoMetadata> findBySubgenre(String subgenre) {
        List<VideoMetadataDAO> daos = videoMetadataRepository.findVideoMetadataDAOBySubgenres_Name(subgenre);
        return daos.stream().map(VideoMetadataService::videoMetadataDaoToDomain).collect(Collectors.toList());
    }

    static void setIfPresentString(Supplier<String> getter, Consumer<String> setter) {
        if (getter.get() != null) {
            setter.accept(getter.get());
        }
    }

    static void setIfPresentInteger(Supplier<Integer> getter, Consumer<Integer> setter) {
        if (getter.get() != null) {
            setter.accept(getter.get());
        }
    }

    static VideoMetadata videoMetadataDaoToDomain(VideoMetadataDAO videoMetadataDAO) {
        return VideoMetadata.builder()
                .setId(videoMetadataDAO.getId())
                .setTitle(videoMetadataDAO.getTitle())
                .setAlbum(videoMetadataDAO.getAlbum())
                .setArtist(videoMetadataDAO.getArtist())
                .setGenre(Genre.fromName(videoMetadataDAO.getGenre().getName()))
                .setSubgenres(videoMetadataDAO.getSubgenres().stream()
                        .map(SubgenreDAO::getName).collect(Collectors.toList()))
                .setReleaseYear(videoMetadataDAO.getReleaseYear())
                .build();
    }

    VideoMetadataDAO domainToDao(VideoMetadata videoMetadata) {
        VideoMetadataDAO dao = new VideoMetadataDAO();
        dao.setId(videoMetadata.getId());
        dao.setTitle(videoMetadata.getTitle());
        dao.setArtist(videoMetadata.getArtist());
        dao.setGenre(genresCache.get(videoMetadata.getGenre().getName()));
        dao.setDuration(videoMetadata.getDuration());
        dao.setAlbum(videoMetadata.getAlbum());
        List<SubgenreDAO> subgenreDAOs = new ArrayList<>();
        for (String subgenre : videoMetadata.getSubgenres()) {
            SubgenreDAO subgenreDAO = new SubgenreDAO();
            subgenreDAO.setName(subgenre);
            subgenreDAOs.add(subgenreDAO);
        }
      //  dao.setSubgenres(subgenreDAOs);
        return dao;
    }
}
