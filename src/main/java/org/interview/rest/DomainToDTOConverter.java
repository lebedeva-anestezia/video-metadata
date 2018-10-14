package org.interview.rest;

import org.interview.domain.model.VideoMetadata;

class DomainToDTOConverter {

    static VideoMetadataDTO videoMetadataToDTO(VideoMetadata videoMetadata) {
        VideoMetadataDTO dto = new VideoMetadataDTO();
        dto.setId(videoMetadata.getId());
        dto.setTitle(videoMetadata.getTitle());
        dto.setArtist(videoMetadata.getArtist());
        dto.setGenre(videoMetadata.getGenre().getName());
        dto.setDuration(videoMetadata.getDuration());
        dto.setAlbum(videoMetadata.getAlbum().isPresent() ? videoMetadata.getAlbum().get() : null);
        dto.setSubgenres(videoMetadata.getSubgenres());
        dto.setReleaseYear(videoMetadata.getReleaseYear());
        return dto;
    }
}
