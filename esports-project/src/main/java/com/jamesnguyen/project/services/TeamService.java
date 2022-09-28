package com.jamesnguyen.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jamesnguyen.project.models.Team;
import com.jamesnguyen.project.repositories.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	public List<Team> allTeams(){
		return teamRepo.findAll();
	}
	
	//create team
	
	public Team createTeam(Team team, BindingResult result) {
		return teamRepo.save(team);
	}
	
	//find
	
	public Team oneTeam(Long id) {
		Optional<Team> potentialTeam = teamRepo.findById(id);
		if(potentialTeam.isPresent()) {
			return potentialTeam.get();
		}else {
			return null;
		}
	}
	
	
	//update
	public Team updateTeam(Team team) {
		return teamRepo.save(team);
	}
	
	
	//delete
	public void deleteTeam(Long id) {
		teamRepo.deleteById(id);
	}
	
	
}
