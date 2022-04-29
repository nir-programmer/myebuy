package org.nir.myebuy.api.user;

import java.util.List;

import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
public class RoleController 
{
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public List<Role> getRoles()
	{
		return this.roleService.getRoles();
	}

	
}
