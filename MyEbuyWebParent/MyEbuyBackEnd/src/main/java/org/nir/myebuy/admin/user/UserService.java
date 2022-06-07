package org.nir.myebuy.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService 
{
	public static final Integer USERS_PER_PAGE = 4; 
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
	
	
	//Note: the page number is 0 based - but the client will pass 1,2,3...
	public Page<User> listByPage(Integer pageNum)
	{
		//Create Pageable of size 
		Pageable pageable = PageRequest.of(pageNum - 1, USERS_PER_PAGE); 
		
		return this.userRepository.findAll(pageable);
	}
	public List<Role> listRoles(){
		return (List<Role>) this.roleRepository.findAll();
	}
	
	//AS ALWAYS - FOR SAVING THERE ARE 2 OPTIONS - EDIT OR CREATE NEW!
	public User save(User user)
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

	public User getUser(Integer id) throws UserNotFoundException {
		
		//Since this method might throw - I want to catch and handle it by rethrow my custom exception 
		try {
			return this.userRepository.findById(id).get();
			
		}
		catch(NoSuchElementException ex) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}
	
	//NOTE: I call the countById() and not getUser() findById() - because I dont want to return a full Object of user - I just want to check existence
	public void deleteUser(Integer userId) throws UserNotFoundException
	{
		Long countById = this.userRepository.countById(userId); 
		
		if(countById == null || countById == 0) 
			throw new UserNotFoundException("Could not find any user with ID " + userId);	
		
		
		this.userRepository.deleteById(userId);
		
	}


	public void updateUserEnableStatus(Integer id, boolean status) 
	{
		this.userRepository.updateEnabledStatus(id, status);
		// TODO Auto-generated method stub
		
	}
	
	public boolean isUniqueEmailViolated(Integer id, String email)
	{
		boolean isUniquEmailViolated = false; 
		
		User userByEmail = this.userRepository.getUserByEmail(email);
		boolean isCreatingNew = (id == null || id == 0); 
		
		if(isCreatingNew)
		{
			if(userByEmail != null) isUniquEmailViolated = true; 
		}
		else 
		{
			if(userByEmail.getId() != id)
			{
				isUniquEmailViolated = true; 
			}
		}
		
		
		return isUniquEmailViolated; 
	}


	//MY METHOD - JUST FOR UNIT TESTING - CONVERT TO LOWER CASE 
	//The repo method works already - tested! 
	public String getEmailById(Integer id) {
		String email = this.userRepository.getEmailById(id);
		System.out.println(">>UserServcie - getEmailById() - repo return email: " + email); 
		
		
		email = email.toLowerCase(); 
		
		
		return email; 
	}
	

}
