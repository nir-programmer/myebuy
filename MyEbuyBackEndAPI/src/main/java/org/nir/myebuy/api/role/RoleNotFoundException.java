package org.nir.myebuy.api.role;


//Step 2 for handling errors using REST!!!
public class RoleNotFoundException extends RuntimeException 
{
//	public RoleNotFoundException(Integer id) {
//		super("Could not find role: " + id);
//		
//	}
//
	public RoleNotFoundException() {
		super();
		
	}

	public RoleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleNotFoundException(String message) {
		super(message);
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);
	}
	

}
