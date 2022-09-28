package com.jamesnguyen.project.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jamesnguyen.project.models.LoginUser;
import com.jamesnguyen.project.models.User;
import com.jamesnguyen.project.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	// Registration
	
	public User register(User newUser, BindingResult result) {
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		if(optionalUser.isPresent()) {
			result.rejectValue("email", "unique", "This email is already in use");
		}
    	if(!newUser.getPassword().equals(newUser.getConfirm())) { 
    		result.rejectValue("confirm", "matches", "The confirm password does not match");
    	}
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return userRepo.save(newUser);
	}

	// Login
	
    public User login(LoginUser newLoginObject, BindingResult result) {
    	
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "unique", "The email is not registered");
    		return null;
    	}
    	
    	User user = potentialUser.get();
    	
    	if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    		result.rejectValue("password", "Matches", "Invalid password");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	return user;
    	
    }
    
    
    public User findbyId(Long id) {

    	Optional<User> potentialUser = userRepo.findById(id);
    	if(potentialUser.isPresent()) {
    		return potentialUser.get();
    	}
    	return null;
    }
    
    public User oneUser(Long id) {
    	Optional<User> optionalUser = userRepo.findById(id);
    	if(optionalUser.isPresent()) {
    		return optionalUser.get();
    	}else {
    		return null;
    	}
    }
    
	
	
	
	
	
	
}
