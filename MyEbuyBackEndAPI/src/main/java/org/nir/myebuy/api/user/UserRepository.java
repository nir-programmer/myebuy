package org.nir.myebuy.api.user;

import org.nir.myebuy.common.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
//@CrossOrigin({"http://localhost:4200", "http://localhost:5501"})
//@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
//@Profile("dev")
//ADD SUPPORT FOR SORT AND PAGINATION - HATEOAS - HOW TO DO IN JAVA - STEP 1!
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	/**
	 * @Query("select t from Test t join User u where u.username = :username")
		List<Test> findAllByUsername(@Param("username")String username);

	 */
	//public List<Role> find

}
