package org.interview.rest;

import org.interview.domain.model.Genre;
import org.interview.domain.model.VideoMetadata;

public class DTOToDomainConverter {

    static VideoMetadata videoMetadata(VideoMetadataDTO dto) {
        return VideoMetadata.builder()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setArtist(dto.getArtist())
                .setReleaseYear(dto.getReleaseYear())
                .setDuration(dto.getDuration())
                .setGenre(Genre.fromName(dto.getGenre()))
                .setSubgenres(dto.getSubgenres())
                .setAlbum(dto.getAlbum())
                .build();
    }
}
