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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//RestController: Indicates the data returned by each method will be written straight into the RESPONSE BODY (instead of rendering a templage)
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501", "http://127.0.0.1:5503"})
public class RoleController 
{
	
	@Autowired
	private RoleService roleService;
	
	
	
	//HOWTODOINJAVA - HATEAOS - PAGING - STEP 2: Create PageModel using PagedResourcesAssembler
	@Autowired
	private RoleModelAssembler roleModelAssembler; 
	
	
	//LATER!! 
	@Autowired
	private PagedResourcesAssembler<Role> pagedResourcesAssembler;
	
	
	@GetMapping("/roles")
	public ResponseEntity<CollectionModel<RoleModel>> getAllRoles() 
	{
		List<Role> roles = this.roleService.getRoles();  
		return new ResponseEntity<>( 
				this.roleModelAssembler.toCollectionModel(roles),HttpStatus.OK);
	}
	  
	
	@GetMapping("roles/{id}") 
	public ResponseEntity<RoleModel> getRoleById(@PathVariable("id") Integer id)
	{
		return this.roleService.getRoleById(id) 
				.map(role -> this.roleModelAssembler.toModel(role)) 
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////
//	@GetMapping("/roles")
//	public CollectionModel<EntityModel<Role>> getRoles()
//	{
//		List<EntityModel<Role>> roles = this.roleService.getRoles().stream()
//				.map(role -> EntityModel.of(role,
//						linkTo(methodOn(RoleController.class)
//						.getRoleById(role.getId())).withSelfRel(),
//						linkTo(methodOn(RoleController.class).getRoles()).withRel("roles")))
//						.collect(Collectors.toList());
//				
//		
//		return CollectionModel.of(roles, linkTo(methodOn(RoleController.class).getRoles()).withSelfRel());
//	}
	
	/**
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
//	@GetMapping("/roles/{id}")
//	public EntityModel<Role> getRoleById(@PathVariable Integer id)
//	{
//		Role role = this.roleService.getRoleById(id).orElseThrow(() -> new RoleNotFoundException("Could not find role : " + id));
//		
//		return EntityModel.of(role,
//				linkTo(methodOn(RoleController.class).getRoleById(id)).withSelfRel(),
//				linkTo(methodOn(RoleController.class).getRoles()).withRel("roles"));
//				
//
////		return role.get();
//	}
	
	
	
	

	
}
