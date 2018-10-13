package org.interview.domain;

import org.interview.domain.model.VideoMetadata;
import org.interview.repository.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ValidationService {

    private final VideoMetadataRepository videoMetadataRepository;

    @Autowired
    public ValidationService(VideoMetadataRepository videoMetadataRepository) {
        this.videoMetadataRepository = videoMetadataRepository;
    }

    public void isCreationParametersValid(VideoMetadata videoMetadata) {
        if (isIdExist(videoMetadata.getId())) {
            throw new IllegalArgumentException("Video Metadata with the passed ID already exist");
        }
        if (!checkYearIsValid(videoMetadata.getReleaseYear())) {
            throw new IllegalArgumentException("The passed release year is invalid");
        }
    }

    public void isUpdateParametersValid(VideoMetadata videoMetadata) {
        if (videoMetadata.getReleaseYear() == null) {
            return;
        }
        if (!checkYearIsValid(videoMetadata.getReleaseYear())) {
            throw new IllegalArgumentException("The passed release year is invalid");
        }
    }

    private boolean isIdExist(Long id) {
        return videoMetadataRepository.existsById(id);
    }

    private boolean checkYearIsValid(Integer year) {
        return year > 0 && year <= Calendar.getInstance().get(Calendar.YEAR);
    }
}
