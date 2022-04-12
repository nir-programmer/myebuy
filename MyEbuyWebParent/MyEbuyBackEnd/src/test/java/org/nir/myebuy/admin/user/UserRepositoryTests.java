package org.nir.myebuy.admin.user;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

		


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests
{
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private TestEntityManager entityManager; 
	
	//OK
	@Test
	public void testCreateTable()
	{
		
	}
	
//	//OK
//	@Test
//	public void testCreateNewUserWithOneRole()
//	{
//		//ARRANGE
//		User nir = new User("niritzhak10@gmail.com", "superduper100", "Nir", "Itzhak"); 
//		Role adminRole = this.entityManager.find(Role.class, 1); 
//		
//		//ACT
//		nir.addRole(adminRole);
//		
//		
//		//ASSERT
//		User savedUser = this.userRepository.save(nir); 
//		assertThat(savedUser).isNotNull();
//		assertThat(savedUser.getId()).isEqualTo(1);
//		assertThat(savedUser.getFirstName()).isEqualTo("Nir");
//		
//		
//	}
//	
//	/**
//	 * GREAT: 
//	 * mysql> select * From users_roles;
//		+---------+---------+
//		| user_id | role_id |
//		+---------+---------+
//		|       1 |       1 |
//		|       2 |       3 |
//		|       2 |       5 |
//		+---------+---------+
//	 */
//	@Test
//	public void testCreateNewUserWithTwoRoles()
//	{
//		//ARRANGE
//		User ravi = new User("ravi@gmail.com", "ravi2022", "Ravi", "Kumar"); 
//		Role roleEditor = new Role(3); 
//		Role roleAssistance = new Role(5); 
//		
//		//ASSERT
//		ravi.addRole(roleAssistance);
//		ravi.addRole(roleEditor);
//		
//		User savedUser = this.userRepository.save(ravi); 
//		
//		assertThat(savedUser).isNotNull();
//		//assertThat(savedUser.getId()).isEqualTo(2);
//		assertThat(savedUser.getFirstName()).isEqualTo("Ravi");
//		assertThat(savedUser.getRoles().size()).isEqualTo(2);
//		
//		
//	}
//	
	@Test
	public void testListAllUsers()
	{
		//GIVEN
		Integer expectedNumberOfUsers = 2; 
		
		//WHEN
		List<User> actualUsers = (List<User>) this.userRepository.findAll(); 
		Integer actualNumberOfUsers = actualUsers.size();
		
		//THEN
		assertThat(expectedNumberOfUsers).isEqualTo(expectedNumberOfUsers); 
		
		
		System.out.println(">>testListAllUsers(): List of all users from DB: "); 
		actualUsers.stream().forEach(System.out::println);
		
	
	}
	
	@Test
	public void testGetUserById()
	{
		//ARRANGE
		Integer id = 15; 
		
		//ACT
		User user = this.userRepository.findById(id).get();
		
		//ASSERT
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(15); 
		
		System.out.println(">>testGetUserById(): The user with id " + id ); 
		System.out.println(user); 
		System.out.println(user.getPhotos());
	}
	
	//PERFECT
//	@Test
//	public void testUpdateUser()
//	{
//		//ARRANGE
//		Integer id = 1; 
//		User user = this.userRepository.findById(id).get();
//		System.out.println(">>testUpdateUser(): Original user from db: " + user); 
//		
//		//ACT
//		user.setFirstName("Niron");
//		user.setEnabled(true);
//		User updatedUser = this.userRepository.save(user); 
//		
//		//ASSERT
//		assertThat(updatedUser).isNotNull();
//		assertThat(updatedUser.getFirstName()).isEqualTo("Niron"); 
//		
//		System.out.println(">>testUpdateUser(): Updated user: " + updatedUser); 
//		
//	}
//
//	/**
//	 * PERFECT
//	 * 				mysql> select * From users_roles;
//					+---------+---------+
//					| user_id | role_id |
//					+---------+---------+
//					|       1 |       1 |
//					|       2 |       3 |
//					|       2 |       5 |
//					+---------+---------+
//					3 rows in set (0.00 sec)
//					
//					mysql> select * From users_roles;
//					+---------+---------+
//					| user_id | role_id |
//					+---------+---------+
//					|       1 |       1 |
//					|       2 |       5 |
//					+---------+---------+
//					2 rows in set (0.00 sec)
//
//	 */
//	//NOTE: SINCE I HAVE IN THE USER ENTITY CLASS HASH CODE ON THE ID - I CAN UPDATE THE SET ON THIS ID 
//	@Test
//	public void testUpdateUserRoles()
//	{
//		//ARRANGE
//		Integer id = 2; 
//		User userRavi = this.userRepository.findById(id).get();
//		System.out.println(">>testUpdateUserRoles():Updated roles of user with id : " + id); 
//		
//		Set<Role> originalRoles = userRavi.getRoles();
//		originalRoles.stream().forEach(System.out::println);
//		
//		//ACT
//		originalRoles.remove(new Role(3));
//		User updatedUserRavi = this.userRepository.save(userRavi); 
//		
//		//ASSERT
//		assertThat(updatedUserRavi).isNotNull();
//		assertThat(updatedUserRavi.getRoles().size()).isEqualTo(1); 
//		
//		System.out.println(">>testUpdateUserRoles():Updated roles of user with id : " + id); 
//		updatedUserRavi.getRoles().stream().forEach(System.out::println);
//	}
	
	/**
	 * PERFECT: 
	 * mysql> select id,first_name From users;
				+----+------------+
				| id | first_name |
				+----+------------+
				|  1 | Niron      |
				|  5 | Ravi       |
				+----+------------+
				2 rows in set (0.00 sec)
				
				mysql> select id,first_name From users;
				+----+------------+
				| id | first_name |
				+----+------------+
				|  1 | Niron      |
				|  5 | RAVI       |
				+----+------------+
				2 rows in set (0.00 sec)

	 */
	@Test 
	void updateUserFirstName()
	{
		//Arrange
		Integer id = 5; 
		User userRavi = this.userRepository.findById(id).get();
		
		
		//ACT
		userRavi.setFirstName("RAVI");
		 User updatedUser = this.userRepository.save(userRavi); 
		
		//ASSERT
		 assertThat(updatedUser.getFirstName()).isEqualTo("RAVI"); 
	
	}
	
//	@Test
//	public void testCreateNewUserWithTwoRolesWithImgURL()
//	{
//		//ARRANGE
//		User shalom = new User("shalom@gmail.com", "shalom2022", "Shalom", "Itzhak"); 
//		Role roleEditor = new Role(3); 
//		Role roleAssistance = new Role(5); 
//		
//		//ASSERT
//		shalom.addRole(roleAssistance);
//		shalom.addRole(roleEditor);
//		shalom.setPhotos("assets/images/users/2/Allada Pavan.png");
//		
//		User savedUser = this.userRepository.save(shalom); 
//		
//		assertThat(savedUser).isNotNull();
//		//assertThat(savedUser.getId()).isEqualTo(2);
//		assertThat(savedUser.getFirstName()).isEqualTo("Shalom");
//		assertThat(savedUser.getRoles().size()).isEqualTo(2);
//		
//		
//	}
//	
	
	
	/**
	 * Hibernate: 
    delete 
    from
        users 
    where
        id=?
	 */
//	@Test
//	public void testDeleteUser()
//	{
//		//ARRANGE
//		Integer id = 10; 
//		
//		
//		//ACT
//		this.userRepository.deleteById(id);
//		
//		Optional<User> userOptional = this.userRepository.findById(10); 
//		
//		//ASSERT
//		assertThat(userOptional.isPresent()).isFalse();
//		
//		
//	}
//	
	@Test
	public void testCreateUserWithImageUrl()
	{
		
		
		
	}
	

}
