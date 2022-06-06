package org.nir.myebuy.admin;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.Transient;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer 
{

	/**
	 * To expose the user-photos directory on the OS for the clients can be access
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName = "user-photos"; 
		// TODO Auto-generated method stub
		
		//Get the path to the user-photos folder on the OS
		Path userPhotosDir = Paths.get(dirName); 
		
		//Get the absulote path of the folder 
		String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
		
		//Add a resource handler to the user-phoots and all it's subfolders will be availbe for the web client(Check for window/UNIX
//		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + userPhotosPath + "/"); 
		//FOR UNIX: 
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:" + userPhotosPath + "/"); 
		
		
	}
	
	
	
	

}
