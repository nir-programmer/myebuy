package org.nir.myebuy.api.user;

import org.nir.myebuy.api.role.RoleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler 
{
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc)
	{
		System.err.println(">>UserRestExceptionHandler - handleExeption for UserNotFoundException!!!");
		//Create the UserErrorResponse 
		UserErrorResponse userErrorResponse = new UserErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		//Return the ResponseEntity with the UserErrorResponse response body
		return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
	}
	
	//@ExceptionHandler
	//Add another exception handler - for any other exception(catch all)
		@ExceptionHandler
		public ResponseEntity<UserErrorResponse> handleException(Exception exc)
		{
			
			System.err.println(">>UserRestExceptionHandler - handleExeption for Exception!!!");

			//create RoleErrorResponse 
			UserErrorResponse roleErrorResponse = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
			
			
			return new ResponseEntity<>(roleErrorResponse, HttpStatus.BAD_REQUEST);
			
			
		}

	
	

}
