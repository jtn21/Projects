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

import com.jamesnguyen.project.models.Comment;
import com.jamesnguyen.project.models.LoginUser;
import com.jamesnguyen.project.models.Student;
import com.jamesnguyen.project.models.User;
import com.jamesnguyen.project.services.CommentService;
import com.jamesnguyen.project.services.StudentService;
import com.jamesnguyen.project.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CommentService commentService;
	
	//@Column(columnDefinition = "LONGTEXT")

	// Login & Registration
	
	@GetMapping("/")
	public String displayLogReg(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin" , new LoginUser());
		return "logregform.jsp";
	}
	
	@PostMapping ("/register")
	public String processRegister(@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model ,HttpSession session) {
		userService.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "logregform.jsp";
		}
		
		session.setAttribute("userId", newUser.getId());
		return "redirect:/dashboard";
	}
	
	
	@PostMapping("/login")
	public String processLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
	User user = userService.login(newLogin, result);
	
	if(result.hasErrors()) {
		model.addAttribute("newUser", new User());
		return "logregform.jsp";
	}
	
	session.setAttribute("userId", user.getId());
	return "redirect:/dashboard";
	}
	
	
	//-----------------Logout------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//session.etAttriute("userId", null)
		session.invalidate();
		return "redirect:/";
	}
	
	//----------Dashboard-------------
	@GetMapping("/dashboard")
	public String displayDashboard(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.findbyId(userId));

		
		return "dashboard.jsp";
	}
	
	
	//---------Create Student--
	@GetMapping("/students/add")
	public String addStudent(@ModelAttribute("student")Student student, HttpSession session ) {
		if(session.getAttribute("userId") ==null) {
			return "redirect:/";
		}
		return "createStudent.jsp";
	}
	
	@PostMapping("/students/add")
	public String processAddStudent(@Valid @ModelAttribute("student")Student student,
			BindingResult result) {
		if (result.hasErrors()) {
			return "createStudent.jsp";
		}else {
			studentService.createStudent(student, result);
			return "redirect:/dashboard";
		}

	}
	
	
	
	//-------Edit Student
	
	@GetMapping("/students/edit/{id}")
	public String editStudents(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") ==null) {
			return "redirect:/";
		}
		model.addAttribute("student", studentService.oneStudent(id));
		return "editStudent.jsp";
	}
	
	
	//process
	@PutMapping("/students/edit/{id}")
	public String proccessEditStudents(@Valid @ModelAttribute("student")Student student, 
			BindingResult result) {
		if (result.hasErrors()) {
			return "editStudent.jsp";
		}
		studentService.updateStudent(student);
		return "redirect:/dashboard";
	}
	
	//find one student
	@GetMapping("/students/{id}")
	public String oneStudent(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") ==null) {
			return "redirect:/";
		}
		model.addAttribute("student", studentService.oneStudent(id));
		return "studentDetails.jsp";
	}
	
	
	
	//delete one student
	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable("id")Long id) {
		studentService.deleteStudent(id);
		return "redirect:/dashboard";
	}
	
	
	
	//Roster List
	@GetMapping("/view/roster")
	public String displayStudents(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//lists all students
		List<Student> studentList = studentService.allStudents();
		model.addAttribute("studentList", studentList);
		return "viewRoster.jsp";
	}
	
	
	//add comment
	@GetMapping("/messages/add")
	public String addComment(@ModelAttribute("comments")Comment comment, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		return "comment.jsp";
				
	}
	
	@PostMapping("/messages/add")
	public String processComment(@Valid @ModelAttribute("comments")Comment comment, BindingResult result) {
		if (result.hasErrors()) {
			return "comment.jsp";
		}else {
			commentService.addComment(comment,result);
		}
		return "redirect:/view/messages";
	}
	
	
	
	//show messages
	@GetMapping("/view/messages")
	public String displayMessages(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Comment> commentList = commentService.allComments();
		model.addAttribute("commentList", commentList);
		return "viewMessage.jsp";
	}
	
	


	
	

}
