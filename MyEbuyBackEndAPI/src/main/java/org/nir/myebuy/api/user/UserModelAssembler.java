package org.nir.myebuy.api.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.nir.myebuy.api.role.RoleModel;
import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;



@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {
	
	public UserModelAssembler()
	{
		super(UserController.class, UserModel.class);
	}

	@Override
	public UserModel toModel(User entity) {
		
		UserModel userModel = instantiateModel(entity); 
		
		//add the link to a single user in the response 
		userModel.add(linkTo(
				methodOn(UserController.class)
				.getUserById(entity.getId()))
				.withSelfRel());
		
		userModel.setId(entity.getId());
		userModel.setEmail(entity.getEmail());
		userModel.setFirstName(entity.getFirstName());
		userModel.setLastName(entity.getLastName());
		userModel.setEnabled(entity.isEnabled());
		userModel.setPhotos(entity.getPhotos());
		userModel.setRoles(toRoleModel(entity.getRoles()));
		
		
		return userModel;
	}

	//IMPORTANT!!! STREAMS : My implementation
		private List<RoleModel> toRoleModel(List<Role> roles) {
			
			//Check requirements - if the user must have a role - if so ??? 
			if(roles.isEmpty())
				return Collections.emptyList();
			
			return roles.stream() 
				.map(role -> RoleModel.builder()
						.id(role.getId())
						.name(role.getName())
						.description(role.getDescription())
						.build() 
						.add(linkTo(
								methodOn(UserController.class)
								.getRoleById(role.getId()))
								.withSelfRel()))
				.collect(Collectors.toList()); 
			

		
		}
//	//IMPORTANT!!! STREAMS : My implementation
//	private List<RoleModel> toRoleModel(Set<Role> roles) {
//		
//		//Check requirements - if the user must have a role - if so ??? 
//		if(roles.isEmpty())
//			return Collections.emptyList();
//		
//		roles.stream() 
//			.map(role -> RoleModel.builder()
//					.id(role.getId())
//					.name(role.getName())
//					.description(role.getDescription())
//					.build() 
//					.add(linkTo(
//							methodOn(UserController.class)
//							.getRoleById(role.getId()))
//							.withSelfRel()))
//			.collect(Collectors.toList()); 
//		
//
//		return null; 
//	}

	
	

}
