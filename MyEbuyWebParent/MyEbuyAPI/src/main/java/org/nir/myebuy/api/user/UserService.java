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
	
	public List<User> listAll()
	{
		return (List<User>) this.userRepository.findAll();
	}

	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id).get();
	}

}
