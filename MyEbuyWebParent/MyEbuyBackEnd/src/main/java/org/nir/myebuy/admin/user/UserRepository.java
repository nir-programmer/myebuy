package org.nir.myebuy.admin.user;

import org.nir.myebuy.common.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;



//public interface UserRepository extends CrudRepository<User, Integer> 
public interface UserRepository extends PagingAndSortingRepository<User, Integer> 
{
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	//Follow the convention by Spring data JPA => I dont need to specify any SQL statement
	public Long countById(Integer id);
	
	
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id=  ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled); 

	
	/************************
	 * My Methods
	 ************************/
	
	@Query("SELECT u.email FROM User u WHERE u.id = :id")
	public String getEmailById(Integer id);
	
}
