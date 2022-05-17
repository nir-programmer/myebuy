package org.nir.myebuy.api.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.nir.myebuy.common.entity.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
public class UserController {
	
	//@Autowired
	private final  UserService userService;
	
	public UserController(UserService userService) {this.userService = userService;}
	
	
	
	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getUsers()
	{
		List<EntityModel<User>> users = this.userService.getUsers().stream()
				.map(user -> EntityModel.of(user,
						linkTo(methodOn(UserController.class)
						.getUser(user.getId())).withSelfRel(),
						linkTo(methodOn(UserController.class).getUsers()).withRel("users")))
						.collect(Collectors.toList());
				
		
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}
	
	
	//CAN NOT GET THE PARENT LINK!!
	//Step 3 for handling errors using REST!!! 
		@GetMapping("/employees/{id}")
		EntityModel<User> getUser(@PathVariable Integer id) {

		  User user = this.userService.getUser(id) 
		      .orElseThrow(() -> new UserNotFoundException(id));

		  return EntityModel.of(user, //
		      linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
		      linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
		}

	//RPC - Before adding links!!! 
//	@GetMapping("/users")
//	public List<User> getUsers(){
//		return this.userService.getUsers();
//	}
	
	
//	
	//Step 3 for handling errors using REST!!! 
	/*
	 * @GetMapping("/users/{id}") public User getUser(@PathVariable Integer id) {
	 * Optional<User> userOptional = this.userService.getUser(id);
	 * if(userOptional.isEmpty()) {
	 * 
	 * throw new UserNotFoundException("User is not found - " + id); }
	 * 
	 * return userOptional.get();
	 * 
	 * 
	 * RESPONSE: 
	 * 
	 * 	{
		  "id" : 1,
		  "email" : "postman@gmail.com",
		  "password" : "$2a$10$ByVsBni9ndOJsP/Isjo.7Oysv7bzqX.QZSA/xCWaRexEeZLzWiT/a",
		  "firstName" : "NIRON",
		  "lastName" : "Mannnn",
		  "photos" : "",
		  "enabled" : false,
		  "_links" : {
		    "self" : {
		      "href" : "http://localhost:8083/MyEbuyAdminAPI/api/users/1"
		    },
		    "user" : {
		      "href" : "http://localhost:8083/MyEbuyAdminAPI/api/users/1"
		    },
		    "roles" : {
		      "href" : "http://localhost:8083/MyEbuyAdminAPI/api/users/1/roles"
		    }
		  }
		
	 * 
	 * }
	 */
	
	
	
	//IMPORTANT OPEN THE HIDDEN COMMENT! 
	
	/*	1.POST - ACCESS THE REQUEST BODY (instead of parsing the JSON by my self
	* 		Jackson will convert request body from JSON to POJO
	*		 @RequestBody - binds the POJO to a method parameter
	*	
	*	2. Set the id to 0 - the saveOrUpdate() Hibernate method: 
	*
	*		since the DAO uses Hibernate that uses following code : 
	*			
	*			public void save(User user)
	*			{
	*				Session currentSession = sessionFactory.getCurrentSession(); 
	*				currentSession.saveOrUpdate(user);
	*			}
	*		
	*		The saveOrUpdate(...) 
	*		if(primaryKey/id) empty  // EMPTY = NULL | 0 
	*			then INSERT new user   
	*		else UPDATE existing user 
	*	
	*	3.POST - HTTP HEADERS: (CLIENT SIDE) 
	*		
	*		When sendign JSON data to Spring REST controllers: 
	*			
	*			For controller to process JSON data - I need to set HTTP request header : 	
	*
	*					Content-type: application/json
	*
	 * 
	 * 
	 */
	@PostMapping("/users")
	public User createUser(@RequestBody User user)
	{
		System.err.println(">>CreateUser() - the user paramter: " + user);
		//Set the id to 0 : if the client sends the POST with an id - ignore it ! 
		user.setId(0);
		
		user =  this.userService.saveUser(user); 
		System.out.println(">>Create User() - the updated user: " + user);
		
		return user; 
		//Return the updated user to the calling program - the DB will set the new id
		
		//return user;
	}
	
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user)
	{
		return this.userService.saveUser(user);
	}
	
	//CHECK HOW TO PASS SEPARATE PARAMTERS!!!!!!!!!!!!
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@RequestBody EmailWithId emailWithId)
	{
		//Read the user id from the hidden form input (required for editing the user) 
		System.out.println(">>UserController - checkDuplicateEmail():\n id: "+  +emailWithId.getId() +  "email: " + emailWithId.getEmail());
		//For simplicity - return a String and not a JSON object 
		return this.userService.isEmailUnique(emailWithId.getId(),emailWithId.getEmail()) ? "OK":"Duplicated";
		
	}
	
	
	

	
	//NAHM - why post --
//	@PostMapping("/users/check_email")
//	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email)
//	{
//		//Read the user id from the hidden form input (required for editing the user) 
//		System.out.println(">>UserController - checkDuplicateEmail():\n id: "+ id + "email: " + email);
//		//For simplicity - return a String and not a JSON object 
//		return this.userService.isEmailUnique(id,email) ? "OK":"Duplicated";
//		
//	}	
	
		
	
}

/**
 * {
    "firstName":"Post", 
    "lastName":"Man",
    "email":"postman@gmail.com",
    "password":"xxxxxxxxx",
    "photos":"",
    "enabled":false,
    "roles":[]
}
 */
 