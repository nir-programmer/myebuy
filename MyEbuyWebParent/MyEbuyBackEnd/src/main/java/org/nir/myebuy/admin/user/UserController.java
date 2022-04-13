package org.nir.myebuy.admin.user;

import java.util.List;

import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listRoles();
		//Fetch all Roles from the DB
		//System.out.println(">>UserController.newUser(): List of all roles in DB:"); 
		listRoles.stream().forEach(System.out::println);
		
		//Create an EMPTY object to map to the Form inputs - and pass the list of roles and enbaled
		User user = new User();
		
		model.addAttribute("user", user);
		user.setEnabled(true);
		model.addAttribute("listRoles", listRoles);
		
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		//System.out.println(user);
		//service.save(user);
		System.out.println("UserContoller: " + user);
		
		this.service.save(user); 
		
		//To add a message in the view to redirect
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
		
		return "redirect:/users";
	}
}