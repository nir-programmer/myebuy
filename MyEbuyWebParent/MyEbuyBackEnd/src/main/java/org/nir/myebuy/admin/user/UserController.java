package org.nir.myebuy.admin.user;

import java.util.List;

import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		user.setEnabled(true);
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create New User");
		
		return "user_form";
	}
	
	//This method will redirect to the to the user list page
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
	
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name="id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		//Get the User object from the DB with the id
		//this method might throw an exception - catch it and handle it by setting an apprpriate message in the UI
		try {
			User user = this.service.getUser(id);
			List<Role> listRoles = this.service.listRoles();
			
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
			model.addAttribute("listRoles", listRoles);
			return "user_form";
		} catch (UserNotFoundException ex) {
			// display the error message in the user list page (after redirecting)
			redirectAttributes.addFlashAttribute("message",ex.getMessage());
			return "redirect:/users"; 
		}
		
		
	}
	
	//WHY GetMapping and not @Delete?? Maybe because it is not from a form?????
	@GetMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable(name="userId") Integer userId, RedirectAttributes redirectAttributes)
	{
		try 
		{
			//User user = this.service.getUser(userId);
			this.service.deleteUser(userId);
			redirectAttributes.addFlashAttribute("message", "The user ID " + userId + " has been deleted successfully");
		}
		catch(UserNotFoundException exc)
		{
			redirectAttributes.addFlashAttribute("message", exc.getMessage());
		}
		
		return "redirect:/users";
		
		
	}
}