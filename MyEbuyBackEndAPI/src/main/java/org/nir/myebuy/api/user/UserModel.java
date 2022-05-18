

package org.nir.myebuy.api.user;

import java.util.List;

import org.nir.myebuy.api.role.RoleModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

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
@Relation(collectionRelation = "users")
@JsonRootName(value ="user")
@JsonInclude(Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {
	
	private Integer id; 
	
	private String email ;
	
	private String password; 
	
	private String firstName; 
	
	private String lastName; 
	
	
	private String photos;
	
	private boolean enabled; 
	
	private List<RoleModel> roles; 
	

}
