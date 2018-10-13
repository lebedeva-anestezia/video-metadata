package org.interview.repository;

import org.interview.dao.VideoMetadataDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoMetadataRepository extends CrudRepository<VideoMetadataDAO, Long> {

    @Query("SELECT v FROM VideoMetadataDAO v JOIN GenreDao g ON v.genre = g.id WHERE g.name = :name")
    List<VideoMetadataDAO> findByGenre(@Param("name") String name);

}