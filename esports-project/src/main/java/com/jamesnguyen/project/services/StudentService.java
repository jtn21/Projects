package com.jamesnguyen.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jamesnguyen.project.models.Student;
import com.jamesnguyen.project.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	
	//find all
	public List<Student> allStudents(){
		return studentRepo.findAll();
	}
	
	
	//find one
	public Student oneStudent(Long id) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		return null;
	}
	
	
	
	//create
	public Student createStudent(Student student, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		return studentRepo.save(student);
	}
	
	//update
	public Student updateStudent(Student student) {
		return studentRepo.save(student);
	}
	
	//delete
	public void deleteStudent(Long id) {
		studentRepo.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
