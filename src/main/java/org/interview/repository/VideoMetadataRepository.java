package org.interview.repository;

import org.interview.dao.VideoMetadataDAO;
import org.interview.domain.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoMetadataRepository extends CrudRepository<VideoMetadataDAO, Long> {

    List<VideoMetadataDAO> findVideoMetadataDAOByGenre_Name(String name);

    List<VideoMetadataDAO> findVideoMetadataDAOBySubgenres_Name(String name);
}