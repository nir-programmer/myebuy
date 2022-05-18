package org.nir.myebuy.api.role;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "roles")
@JsonRootName(value ="role")
@JsonInclude(Include.NON_NULL)
public class RoleModel extends RepresentationModel<RoleModel> {
	
	private Integer id;
	
	private String name; 
	
	private String description; 
	
	
	//Check this - since I dont have this in the Role Entity (I dont use bi-directional)
	//private List<UserModel>users 
}
