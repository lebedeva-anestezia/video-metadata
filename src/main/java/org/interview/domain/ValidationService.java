package org.interview.domain;

import org.interview.domain.model.VideoMetadata;
import org.interview.repository.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ValidationService {

    private static final int FIRST_CLIP_YEAR = 1926;
    private final VideoMetadataRepository videoMetadataRepository;

    @Autowired
    public ValidationService(VideoMetadataRepository videoMetadataRepository) {
        this.videoMetadataRepository = videoMetadataRepository;
    }

    public void checkIfCreationParametersValid(VideoMetadata videoMetadata) {
        if (isIdExist(videoMetadata.getId())) {
            throw new IllegalArgumentException("Video Metadata with the passed ID already exist");
        }
        if (!checkYearIsValid(videoMetadata.getReleaseYear())) {
            throw new IllegalArgumentException("The passed release year is invalid");
        }
    }

    public void checkIfUpdateParametersValid(VideoMetadata videoMetadata) {
        if (videoMetadata.getReleaseYear() != null && !checkYearIsValid(videoMetadata.getReleaseYear())) {
            throw new IllegalArgumentException("The passed release year is invalid");
        }
        if (!isIdExist(videoMetadata.getId())) {
            throw new IllegalArgumentException("Video Metadata with the passed ID does not exist");
        }
    }

    public void checkIfDeleteParametersValid(Long videoMetadataId) {
        if (!isIdExist(videoMetadataId)) {
            throw new IllegalArgumentException("Video Metadata with the passed ID does not exist");
        }
    }

    private boolean isIdExist(Long id) {
        return videoMetadataRepository.existsById(id);
    }

    private boolean checkYearIsValid(Integer year) {
        return year >= FIRST_CLIP_YEAR && year <= Calendar.getInstance().get(Calendar.YEAR);
    }
}
