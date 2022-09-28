package com.jamesnguyen.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jamesnguyen.project.models.Comment;

public interface CommentRepo  extends CrudRepository<Comment,Long>{
	List<Comment> findAll();
}
