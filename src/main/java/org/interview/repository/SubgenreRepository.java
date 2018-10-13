package org.interview.repository;

import org.interview.dao.SubgenreDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubgenreRepository extends CrudRepository<SubgenreDAO, Long> {

    List<SubgenreDAO> findByNameIn(List<String> names);
}
