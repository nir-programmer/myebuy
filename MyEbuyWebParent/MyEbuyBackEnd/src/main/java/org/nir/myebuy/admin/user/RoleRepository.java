package org.nir.myebuy.admin.user;

import org.nir.myebuy.common.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> 
{
	

}
