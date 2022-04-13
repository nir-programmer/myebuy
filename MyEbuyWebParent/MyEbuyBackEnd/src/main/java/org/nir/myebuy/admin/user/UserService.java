package org.nir.myebuy.admin.user;

import java.util.List;

import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> listAll()
	{
		return (List<User>) this.userRepository.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) this.roleRepository.findAll();
	}
	
	public void save(User user)
	{
		this.userRepository.save(user); 
	}
	

}
