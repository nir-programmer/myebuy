package org.nir.myebuy.admin.user;

import java.util.List;

import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController
{
	@Autowired
	private UserService userService; 
	
	@GetMapping("/users")
	public String listAll(Model model)
	{
		List<User> listUsers = this.userService.listAll();
		
		model.addAttribute("listUsers", listUsers); 
		
		return "users";
		
	}

}
