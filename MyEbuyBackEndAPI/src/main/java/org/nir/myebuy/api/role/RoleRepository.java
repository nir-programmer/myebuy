package org.nir.myebuy.api.role;

import org.nir.myebuy.common.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Access to XMLHttpRequest at 'http://localhost:8083/MyEbuyAdminAPI/api/users' 
 * from origin 'http://localhost:4200' has been blocked by CORS policy: 
 * No 'Access-Control-Allow-Origin' header is present on the requested resource.
 * @author nir
 *
 */
//http://127.0.0.1:5501
//:4200/welcome:1 Access to XMLHttpRequest at 'http://localhost:8083/MyEbuyAdminAPI/api/users' from origin 'http://localhost:4200
//@CrossOrigin("http://localhost:4200")
//@CrossOrigin({"http://localhost:4200", "http://localhost:5501"})
//@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
//@Profile("dev")
public interface RoleRepository extends CrudRepository<Role, Integer> 
{
	

}
