package org.nir.myebuy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController 
{
	@GetMapping("")
	public String getMessage()
	{
		return "Hello World";
	}

}
