package org.nir.myebuy.api.role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Step 4 for handling errors using REST!!!
@ControllerAdvice
public class RoleRestExceptionHandler {
	
	//Add an exception handler for RoleNotFoundException
	//RoleErrorResponse - the type of the response BODY , RoleNotFoundException - the caught exception I need to handle
	@ExceptionHandler
	public ResponseEntity<RoleErrorResponse> handleException(RoleNotFoundException exc)
	{
		
		//create RoleErrorResponse 
		RoleErrorResponse roleErrorResponse = new RoleErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		return new ResponseEntity<>(roleErrorResponse, HttpStatus.NOT_FOUND);
		
		
	}
	
	//Add another exception handler - for any other exception(catch all)
	@ExceptionHandler
	public ResponseEntity<RoleErrorResponse> handleException(Exception exc)
	{
		
		//create RoleErrorResponse 
		RoleErrorResponse roleErrorResponse = new RoleErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
		
		
		return new ResponseEntity<>(roleErrorResponse, HttpStatus.BAD_REQUEST);
		
		
	}
	

}
