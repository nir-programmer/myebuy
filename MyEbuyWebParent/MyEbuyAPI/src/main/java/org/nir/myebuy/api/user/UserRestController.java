package org.nir.myebuy.api.user;

import java.util.List;

import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserRestController
{
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/users")
	public List<User> listAll()
	{
		return this.userService.listAll();
	}
	
	//@CrossOrigin(origins = "http://localhost:8082/MyEbuyAPI/users/{id}")
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id)
	{
		return this.userService.getUser(id); 
	}
	
	
	
	
	

}
