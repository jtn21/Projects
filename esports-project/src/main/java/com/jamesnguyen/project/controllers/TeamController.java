package com.jamesnguyen.project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamesnguyen.project.models.Student;
import com.jamesnguyen.project.models.Team;
import com.jamesnguyen.project.services.StudentService;
import com.jamesnguyen.project.services.TeamService;


@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private StudentService studentService;
	
	
	
	// Create Team
	
	@GetMapping("/teams/add")
	public String addTeam(@ModelAttribute("team")Team team, HttpSession session) {
		if(session.getAttribute("userId") ==null) {
			return "redirect:/";
		}
		return "createTeam.jsp";
	}
	
	@PostMapping("/teams/add")
	public String processAddTeam(@Valid @ModelAttribute("teams")Team team, BindingResult result) {
		if (result.hasErrors()) {
			return "createTeam.jsp";
		}else {
			teamService.createTeam(team, result);
			return "redirect:/dashboard";
		}
	}
	
	
	
	//------Edit Team-------------------------------
	@GetMapping("/teams/edit/{id}")
	public String editTeams(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") ==null) {
			return "redirect:/";
		}
		model.addAttribute("team", teamService.oneTeam(id));
		return "editTeam.jsp";
	}
	
	//process edit team
	@PutMapping("/teams/edit/{id}")
	public String processEditTeams(@Valid @ModelAttribute("team")Team team, BindingResult result) {
		if (result.hasErrors()) {
			return "editTeam.jsp";
		}
		teamService.updateTeam(team);
		return "redirect:/dashboard";
	}
	
	
	//find one team
	@GetMapping("/teams/{id}")
	public String oneTeam(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") ==null) {
			return "redirect:/";
		}
		model.addAttribute("team", teamService.oneTeam(id));
		return "teamDetails.jsp";
	}
	
	
	//delete one team
	@DeleteMapping("/teams/{id}")
	public String deleteTeam(@PathVariable("id")Long id) {
		teamService.deleteTeam(id);
		return "redirct:/dashboard";
	}

	
	
	//view teams
	@GetMapping("/view/teams")
	public String displayTeams(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Team> teamList = teamService.allTeams();
		model.addAttribute("teamList", teamList);
		return "viewTeams.jsp";
	}
	
	
	//join team
//	@RequestMapping("/view/teams/join/{id}")
//	public String joinTeam(@PathVariable("id") Long id, HttpSession session, Model model) {
//		if(session.getAttribute("userId") == null) {
//			return "redirect:/";
//		}
//		
//		Team team = teamService.oneTeam(id);
//		Student student = studentService.oneStudent(id);
//		
//		
//		student.getTeam().add(team);
//	
//	}
	
	
	
	
	
	
	
	

}
