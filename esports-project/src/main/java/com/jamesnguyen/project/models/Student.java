package com.jamesnguyen.project.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="student")
public class Student {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=2, max=200, message="First name must be at least 2 characters")
	private String stuFirstName;
	
	
	@NotNull
	@Size(min=2, max=200, message="Last name must be at least 2 characters")
	private String stuLastName;
	
	
	@NotNull
	@Size(min=2, max=200, message="Username must be at least 2 characters")
	private String username;
	
	
	@NotNull
	@NotEmpty(message= "Please select a game")
	private String game;
	
	@Size(min=2, max=300)
	private String notes;
	
	
	//Many to one
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	//Many to one
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="team_id")
	private Team team;
	
	
	
	public Student() {
		
	}
	
	
	
	
	
	//Setters & Getters

	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStuFirstName() {
		return stuFirstName;
	}


	public void setStuFirstName(String stuFirstName) {
		this.stuFirstName = stuFirstName;
	}


	public String getStuLastName() {
		return stuLastName;
	}


	public void setStuLastName(String stuLastName) {
		this.stuLastName = stuLastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGame() {
		return game;
	}


	public void setGame(String game) {
		this.game = game;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
