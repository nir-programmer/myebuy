/**
 * This assembler will be used to convert the JPA User Entity class to DTO object (entity and collection representations) i.e: 
 * 		Role to RoleModel
 * 		User to UserModel 
 * 
 * 
 * NOTE: The RepesentationModelAssemblerSuppoert class implements RepresentationModelAssembler interface . 
 * 
 * 		It provides toModel() and toCollectionModel() methods 
 */
package org.nir.myebuy.api.role;

/**
 * IMPORTANT IMPORTS:
 * import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
 * 
 */
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.nir.myebuy.common.entity.Role;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class RoleModelAssembler extends RepresentationModelAssemblerSupport<Role, RoleModel> {

	
	
	public RoleModelAssembler()
	{
		super(RoleController.class, RoleModel.class);
	}


	@Override
	public RoleModel toModel(Role entity) {
		RoleModel roleModel = instantiateModel(entity);  
		
		//Build the link to a single Role in the response
		roleModel.add(linkTo(methodOn(RoleController.class)
				.getRoleById(entity.getId()))
			.withSelfRel());
		
		
		//Set the RoleModel (DTO) 
		roleModel.setId(entity.getId());  
		roleModel.setName(entity.getName());
		roleModel.setDescription(entity.getDescription());
		
		
		return roleModel;
	}


	@Override
	public CollectionModel<RoleModel> toCollectionModel(Iterable<? extends Role> entities) {
		
		
		CollectionModel<RoleModel> roleModels = super.toCollectionModel(entities);  
		
		roleModels.add(linkTo(methodOn(RoleController.class).getAllRoles()).withSelfRel());
		
		return roleModels; 
	}
	
	
	
	
	
	

}
