package com.jamesnguyen.project.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jamesnguyen.project.models.User;

public interface UserRepository extends CrudRepository<User,Long> {

	Optional<User>findByEmail(String email);
}
