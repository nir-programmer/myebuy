package org.nir.myebuy.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@RequestParam("email") String email)
	{
		//For simplicity - return a String and not a JSON object 
		return this.userService.isEmailUnique(email) ? "OK":"Duplicated";
		
	}

}
