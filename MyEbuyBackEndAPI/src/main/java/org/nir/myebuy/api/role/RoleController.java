package org.nir.myebuy.api.role;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nir.myebuy.api.user.UserController;
import org.nir.myebuy.api.user.UserNotFoundException;
import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//RestController: Indicates the data returned by each method will be written straight into the RESPONSE BODY (instead of rendering a templage)
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
public class RoleController 
{
	
	private RoleService roleService;
	
	public RoleController(RoleService roleService) {this.roleService = roleService;}
	
	
	/**
	 * @GetMapping("/employees")
CollectionModel<EntityModel<Employee>> all() {

  List<EntityModel<Employee>> employees = repository.findAll().stream()
      .map(employee -> EntityModel.of(employee,
          linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
          linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
      .collect(Collectors.toList());

  return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
}
	 * @return
	 */
	//DONE!
	//Aggregate root 
	// tag:: get-aggregate-root[]
	@GetMapping("/roles")
	public CollectionModel<EntityModel<Role>> getRoles()
	{
		List<EntityModel<Role>> roles = this.roleService.getRoles().stream()
				.map(role -> EntityModel.of(role,
						linkTo(methodOn(RoleController.class)
						.getRole(role.getId())).withSelfRel(),
						linkTo(methodOn(RoleController.class).getRoles()).withRel("roles")))
						.collect(Collectors.toList());
				
		
		return CollectionModel.of(roles, linkTo(methodOn(RoleController.class).getRoles()).withSelfRel());
	}	/**
	 * @GetMapping("/employees/{id}")
		EntityModel<User> getUser(@PathVariable Integer id) {

		  User user = this.userService.getUser(id) 
		      .orElseThrow(() -> new UserNotFoundException(id));

		  return EntityModel.of(user, //
		      linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
		      linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
		}
	 * @param id
	 * @return
	 */
	
	//Step 3 for handling errors using REST!!!
	@GetMapping("/roles/{id}")
	public EntityModel<Role> getRole(@PathVariable Integer id)
	{
		Role role = this.roleService.getRole(id).orElseThrow(() -> new RoleNotFoundException("Could not find role : " + id));
		
		return EntityModel.of(role,
				linkTo(methodOn(RoleController.class).getRole(id)).withSelfRel(),
				linkTo(methodOn(RoleController.class).getRoles()).withRel("roles"));
				
//		Optional<Role> role =  this.roleService.getRole(id);
//		if(role.isEmpty())
//			throw new RoleNotFoundException("Role is not found - " + id);
//		
//		return role.get();
	}
	
	
	
	

	
}
