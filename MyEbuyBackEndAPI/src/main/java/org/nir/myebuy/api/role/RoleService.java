package org.nir.myebuy.api.role;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService 
{
	//@Autowired
	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	
	public List<Role> getRoles(){
		return (List<Role>) this.roleRepository.findAll();
	}


	public Optional<Role> getRoleById(Integer id) {
		
		//return this.roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		
		Optional<Role> role ;
		
		try {
		 role =  this.roleRepository.findById(id);
		 //System.err.println("RoleService - getRole() - role: " + role);
		}
		catch(NoSuchElementException exc) {
			System.err.println("RoleService - getRole():\n"+ exc.getMessage() + "\n" + exc);
			throw new RuntimeErrorException(null);
			
		}
		
		return role; 
	}

}
