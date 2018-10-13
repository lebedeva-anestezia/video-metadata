package org.interview.domain;

import org.interview.domain.model.VideoMetadata;
import org.interview.repository.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final VideoMetadataRepository videoMetadataRepository;

    @Autowired
    public ValidationService(VideoMetadataRepository videoMetadataRepository) {
        this.videoMetadataRepository = videoMetadataRepository;
    }

    public boolean isCreationParametersValid(VideoMetadata videoMetadata) {
        return true;
    }

    public boolean isUpdateParametersValid(VideoMetadata videoMetadata) {
        return true;
    }

    public boolean isDeleteParametersValid(Long id) {
        return true;
    }
}
