package org.interview.repository;

import org.interview.dao.VideoMetadataDAO;
import org.springframework.data.repository.CrudRepository;

public interface VideoMetadataRepository extends CrudRepository<VideoMetadataDAO, Long> {
}