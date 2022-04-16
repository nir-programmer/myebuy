package org.nir.myebuy.api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer
{
	@Autowired
	private EntityManager entityManager ;

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
		
		//I added this since the JSON does not include the id - DOES NOT WORK FOR ROLE!
		//config.exposeIdsFor(Role.class);
		
		
		config.getExposureConfiguration()
		.forDomainType(User.class)
		.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		//WORKS - ONLY FOR USER
		//config.exposeIdsFor(User.class);
		
		//Call an internal helper method to expose the ids in the ProductCategory Level of the REST response
        exposeIds(config);
	}
	
	private void exposeIds(RepositoryRestConfiguration config)
    {
        //expose entity ids

        //1. Get a list of all entity classes from the entity manager
       Set<EntityType<?>> entities =  this.entityManager.getMetamodel().getEntities();

       //2. Create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        //3.Get the entity types for entities
        for(EntityType entityType : entities)
        {
            entityClasses.add(entityType.getJavaType());
        }

        //4.Expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        /**
         * Test - list all Entity types in the app:
         * >>MyDataRestConfig: The domain types array:
         * class org.nir.ecommerce.entity.Product
         * class org.nir.ecommerce.entity.ProductCategory
         * 2022
         */
        //System.out.println(">>MyDataRestConfig: The domain types array: ");


        entityClasses.stream().forEach(System.out::println);
        /**NOTE:
         * Set the list of domain types for which we will expose the ID value as a normal property.
         * Params:
         * domainTypes – Array of types to expose IDs for.
         * Returns:
         * this
         */
        config.exposeIdsFor(domainTypes);
    }

}
