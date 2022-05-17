package org.nir.myebuy.admin.user;

import org.nir.myebuy.common.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> 
{
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	//Follow the convention by Spring data JPA => I dont need to specify any SQL statement
	public Long countById(Integer id);

	
}
