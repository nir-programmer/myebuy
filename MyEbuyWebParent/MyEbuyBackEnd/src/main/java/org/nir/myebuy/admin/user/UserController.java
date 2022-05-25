package org.nir.myebuy.admin.user;

import java.util.List;

import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listFirstPage(Model model) {
		
		return this.listByPage(1, model);
	}
	
	//PAGINATION//WORKS
	@GetMapping("/users/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") Integer pageNum ,Model model)
	{
		Page<User> page =  this.service.listByPage(pageNum);
		
		List<User> listUsers = page.getContent();
		
//		System.out.println("listByPage() - pageNum: " + pageNum); 
//		System.out.println("listByPage() - total elements: " + page.getTotalElements());
//		System.out.println("listByPage() - total pages: " + page.getTotalPages()); 
		
		
		//Caluclate the start count(first item in the requested page
		long startCount = (pageNum - 1) * UserService.USERS_PER_PAGE  + 1; 
		//Caluclate the last count(last item in the requested page
		long endCount = startCount + UserService.USERS_PER_PAGE - 1; 
		
		if(endCount > page.getTotalElements())
			endCount = page.getTotalElements(); 
			
		
		//UPDATE THE MODEL
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("startCount", startCount); 
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems",page.getTotalElements());
	
		
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
	
	
	//ADD reference to redirect attribute for redirecting to user list after updating
	//Disable/Enable user status : th:href="@{'/users/' + ${user.id} + '/enabled/false'}"></a>
	@GetMapping("/users/{id}/enabled/{status}")
	public String updateUserEnableStatus(@PathVariable(name ="id") Integer id, @PathVariable(name = "status") boolean enabled, RedirectAttributes redirectAttributes)
	{
		System.out.println("UserController: updateUserEnabledStatus before updating: " + enabled); 
		this.service.updateUserEnableStatus(id, enabled); 
		
		String status = enabled ? "enabled" : "disabled" ; 
		String message = "The user ID " + id + " has been " + status;
		
		//Add the message to the model 
		redirectAttributes.addFlashAttribute("message", message); 
		
		
		return "redirect:/users";
	}
	
	
	
	
	
}