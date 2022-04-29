package org.nir.myebuy.api.user;

import java.util.List;

import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService 
{
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getRoles(){
		return this.roleRepository.findAll();
	}

}
