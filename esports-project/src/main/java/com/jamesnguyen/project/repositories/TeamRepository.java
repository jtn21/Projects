package com.jamesnguyen.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jamesnguyen.project.models.Team;

public interface TeamRepository extends CrudRepository<Team,Long> {
	List<Team> findAll();
}
