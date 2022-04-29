package org.nir.myebuy.api.user;

import java.util.List;

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
	
	public List<User> getUsers(){
		return this.userRepository.findAll();
	}

}
