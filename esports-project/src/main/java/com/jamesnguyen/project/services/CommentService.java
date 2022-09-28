package com.jamesnguyen.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jamesnguyen.project.models.Comment;
import com.jamesnguyen.project.repositories.CommentRepo;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	//get comments
	public List<Comment> allComments(){
		return commentRepo.findAll();
	}
	
	
	//add comment
	public Comment addComment(Comment comment, BindingResult result) {
		return commentRepo.save(comment);
	}
	
	
	//delete comment
	public void deleteComment(Comment comment) {
		commentRepo.delete(comment);
	}
	
}
