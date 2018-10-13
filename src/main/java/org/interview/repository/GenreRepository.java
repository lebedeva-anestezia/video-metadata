package org.interview.repository;

import org.interview.dao.GenreDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreDAO, Long> {

    GenreDAO findByName(String name);
}
