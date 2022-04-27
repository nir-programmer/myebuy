package org.nir.myebuy.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController 
{
	@Autowired
	private UserService userService;
	
	//WHY @PARAM AND NOT @REQUESTPARAM????????????????????????????????
	//When Editing the user - I need additional request param for the user id from the hidden input form !
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email)
	{
		//Read the user id from the hidden form input (required for editing the user) 
		
		//For simplicity - return a String and not a JSON object 
		return this.userService.isEmailUnique(id,email) ? "OK":"Duplicated";
		
	}
	
	/**
	 * @PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("email") String email) {
		return service.isEmailUnique(email) ? "OK" : "Duplicated";
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
		return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
	}
}
	 */
//Resolved [org.springframework.web.bind.MissingServletRequestParameterException: Required request parameter 'id' for method parameter type Integer is present but converted to null
}
