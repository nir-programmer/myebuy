package org.nir.myebuy.api.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.nir.myebuy.api.role.RoleRepository;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	
	public static final Integer USERS_PER_PAGE = 4; 
	
	
	//@Autowired
	private final  UserRepository userRepository; 
	
	
	private final PasswordEncoder passwordEncoder; 

	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder ) 
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	//NO PAGINATION:
	public List<User> getAllUsers(){
		return (List<User>) this.userRepository.findAll();
	}
	
	
	//Pagination - NHAM(webmvc) 
	public Page<User> listByPage(Integer pageNum)
	{
		Pageable pageable = PageRequest.of(pageNum, USERS_PER_PAGE); 
		
		return this.userRepository.findAll(pageable);
	}
	
//	//Howtodoinjava!!!!!
//	public Page<User> listByPage(Pageable pageable)
//	{
//		return this.userRepository.findAll(pageable);
//	}
	
	
	
	
	//It works with Set as well (Nahm did define List like me ) 
//	public Set<User> getUsers(){
//		return (Set<User>) this.userRepository.findAll();
//	}
	
	/**
	 * public Optional<Role> getRole(Integer id) {
		
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
	 * @param id
	 * @return
	 */
	public Optional<User> getUser(Integer id) 
	{
		Optional<User> userOptional; 
		
		try {
			userOptional = this.userRepository.findById(id);
		}
		catch(NoSuchElementException exc)
		{
			System.err.println("RoleService - getRole():\n"+ exc.getMessage() + "\n" + exc);
			throw new RuntimeErrorException(null);
		}
		return userOptional;
	}

	public User saveUser(User user) 
	{
		//this.userRepository.save(user);
		
		//NAHM ---
		boolean isUpdatingUser = (user.getId() != null);
		
		//EDIT MODE - udpate the password
		if(isUpdatingUser)
		{
			//Get the user by id
			Optional<User> existingUser = this.userRepository.findById(user.getId());
			//System.out.println(">>UserService - createUser() - existingUser: " + existingUser);

			//Password is empty 
			if(user.getPassword().isEmpty())
			{
				user.setPassword(existingUser.get().getPassword());
			}
			//Password is not empty in the form - the admin wants to update it's password in the db 
			else
			{
				//encode the user password from the form 
				encodePassword(user); 
			
			}
		}
		//New Mode: Enecode the password before saving in the DB 
		else {
			System.out.println(">>UserService - createUser() - new User!");
			this.encodePassword(user);
		}
		
		//System.out.println(">>UserService - createUser() - user: " + user);
		return this.userRepository.save(user); 
		 
		
	}
	
	
	private void encodePassword(User user)
	{
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
	}
	
	
	public boolean isEmailUnique(Integer id, String email)
	{
		System.err.println("Id: " + id);
		User userByEmail=  this.userRepository.getUserByEmail(email);
		
		//no user found with the given email - return true
		if(userByEmail == null)
			return true; 
		
		//no user found with the given email AND the given id - new mode!
		boolean isCreatingNew = (id == null);
		
		
		if(isCreatingNew)
		{
			//New Mode: There is an existing user with the given email - return false! I need unique email !
			if(userByEmail != null) return false; 
			
		}
		//Edit Mode: There is a user with the given email and this
		else {
			//Compare the email of the edited user against the passing email - if they not equal - return false
			if(userByEmail.getId() != id)
				return false; 
				
		}
		
		//
		return true;
	}



}
