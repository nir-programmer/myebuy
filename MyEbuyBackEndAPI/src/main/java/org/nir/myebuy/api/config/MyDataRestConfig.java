package org.nir.myebuy.api.config;

import javax.management.relation.Role;

import org.nir.myebuy.common.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer
{

	//I will use the CorsRegistry parameter later - to configure the CORS support in the app
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) 
	{
		//Unsupported actions: 
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		
		//Disable HTTP methods for User and Roles : PUT, POST, DELETE
		config.getExposureConfiguration()
			.forDomainType(Role.class)
			.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
			.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		//I added this since the JSON does not include the id
		config.exposeIdsFor(Role.class);
		
		
		config.getExposureConfiguration()
		.forDomainType(User.class)
		.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		config.exposeIdsFor(User.class);
	}
	

}
