package org.nir.myebuy.api.dao;

import org.nir.myebuy.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Access to XMLHttpRequest at 'http://localhost:8083/MyEbuyAdminAPI/api/users' 
 * from origin 'http://localhost:4200' has been blocked by CORS policy: 
 * No 'Access-Control-Allow-Origin' header is present on the requested resource.
 * @author nir
 *
 */
//:4200/welcome:1 Access to XMLHttpRequest at 'http://localhost:8083/MyEbuyAdminAPI/api/users' from origin 'http://localhost:4200
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("http://localhost:4200")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
