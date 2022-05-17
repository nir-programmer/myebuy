package org.nir.myebuy.api.role.response;

import java.util.ArrayList;
import java.util.List;

import org.nir.myebuy.common.entity.Role;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleEmbedded {
	
	@JsonProperty("roles")
	List<Role> roles = new ArrayList<>(); 
	
	public RoleEmbedded() {
		// TODO Auto-generated constructor stub
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
}
