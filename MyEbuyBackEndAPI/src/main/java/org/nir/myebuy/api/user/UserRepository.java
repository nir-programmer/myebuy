package org.nir.myebuy.api.user;

import org.nir.myebuy.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//@CrossOrigin({"http://localhost:4200", "http://localhost:5501"})
//@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
//@Profile("dev")
//ADD SUPPORT FOR SORT AND PAGINATION - HATEOAS - HOW TO DO IN JAVA - STEP 1!
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	
	
	//Note: "containing  - similar to SQL "LIKE": 
	//Behind the scene: 
	/**
	 *  SELECT * FROM User u  WHERE u.name LIKE CONCAT('%', :name, '%')
	
	 */
	
	//WORKS!!!!!!!!!!!!!!!!!!!!!
	  @Query Page<User> findByFirstNameContaining(String firstName, Pageable pageable);
	 



	//public String getEmailById(Integer id);



	//public Page<User> findAll(String keyword, Pageable pageable);
	
	
	
	//NOTE: NHAM SOLUTION: 
//	@Query("SELECT u FROM User u WHERE u.firstName LIKE %?1% OR u.lastName LIKE %?1% OR u.email LIKE %?1%")
	//CONCAT
	/*
	 * @Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ',u.email, ' ' ,u.firstName, ' ', u.lastName) LIKE %?1%"
	 * ) public Page<User> findAll(String keyword, Pageable pageable);
	 */
	
	
	
	
	/**
	 * @Query("select t from Test t join User u where u.username = :username")
		List<Test> findAllByUsername(@Param("username")String username);

	 */
	//public List<Role> find

}
