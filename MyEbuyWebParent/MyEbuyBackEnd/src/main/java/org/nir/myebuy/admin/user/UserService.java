package org.nir.myebuy.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	public List<User> listAll()
	{
		return (List<User>) this.userRepository.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) this.roleRepository.findAll();
	}
	
	//AS ALWAYS - FOR SAVING THERE ARE 2 OPTIONS - EDIT OR CREATE NEW!
	public void save(User user)
	{
		
		boolean isUpdatingUser = (user.getId() != null);
		
		//EDIT MODE - udpate the password
		if(isUpdatingUser)
		{
			//Get the user by id
			User existingUser = this.userRepository.findById(user.getId()).get();
		
			if(user.getPassword().isEmpty())
			{
				user.setPassword(existingUser.getPassword());
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
			this.encodePassword(user);
		}
		
		//In any case - persist the user
		this.userRepository.save(user); 
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

	public User getUser(Integer id) throws UserNotFoundException {
		
		//Since this method might throw - I want to catch and handle it by rethrow my custom exception 
		try {
			return this.userRepository.findById(id).get();
			
		}
		catch(NoSuchElementException ex) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}
	

}
