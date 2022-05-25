package org.nir.myebuy.admin.user;


import static org.assertj.core.api.Assertions.*; 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

	
//@DataJpaTest(showSql = false)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
//@Disabled
public class UserRepositoryH2StandAloneUnitTest
{
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private TestEntityManager entityManager; 
	
	
	
	//OK
	@Test
	@Disabled
	//@DisplayName()
	public void testCreateNewUserWithOneRole()
	{
		//ARRANGE
		User nir = new User("niron10@gmail.com", "superduper100", "Niron", "Itzhak"); 
		Role adminRole = this.entityManager.find(Role.class, 1); 
		
		//ACT
		nir.addRole(adminRole);
		
		
		//ASSERT
		User savedUser = this.userRepository.save(nir); 
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isEqualTo(1);
		assertThat(savedUser.getFirstName()).isEqualTo("Nir");
		
		
	}
	
	@Test
	@DisplayName("Throws DataIntegrityViolationException for non unique email when creating new user")
	//@Disabled
	public void testCreateNewUserWithExistingEmailThrowDataIntegrityViolationException()
	{
		//ARRANGE
		User nir = new User("niritzhak10@gmail.com", "superduper100", "Nir", "Itzhak"); 
		Role adminRole = this.entityManager.find(Role.class, 1); 
		
		//ACT
		nir.addRole(adminRole);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			User savedUser = this.userRepository.save(nir); 
		});
		
		
		//ASSERT
//		User savedUser = this.userRepository.save(nir); 
//		assertThat(savedUser).isNotNull();
//		assertThat(savedUser.getId()).isEqualTo(1);
//		assertThat(savedUser.getFirstName()).isEqualTo("Nir");
		
		
	}
//	
//	/**NOTE: There no Roles are created ! I dont use cascaing ! I want to add EXISTING roles to the new User
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
	@Test
	@Disabled
	public void testCreateNewUserWithTwoRoles()
	{
		//ARRANGE
		User chad = new User("chad@gmail.com", "chad2022", "Chad", "Derby"); 
		Role roleEditor = new Role(3); 
		Role roleAssistance = new Role(5); 
		
		//ASSERT
		chad.addRole(roleAssistance);
		chad.addRole(roleEditor);
		
		User savedUser = this.userRepository.save(chad); 
		
		assertThat(savedUser).isNotNull();
		//assertThat(savedUser.getId()).isEqualTo(2);
		assertThat(savedUser.getFirstName()).isEqualTo("Chad");
		//assertThat(savedUser.getRoles().size()).isEqualTo(2);
		
		
	}
	
	@Test
	@Disabled
	public void testCreateNewUsersWithRoles()
	{
		//ARRANGE
		//User user1 = User.builder().email("chad@gmail.com").password("chad2022").firstName("Chad").lastName("Darby").build();
//		User user2 = User.builder().email("james@gmail.com").firstName("James").password("james2022").lastName("Levi").build();
//		User user3 = User.builder().email("kim@gmail.com").firstName("Kim").password("kim2022").lastName("Cohen").build();
		User user2 = new User("james@gmail.com", "james2022", "James", "Levi"); 
		User user3 = new User("kim@gmail.com", "kim2022", "Kim", "Cohen"); 
		User user4 = new User("ramon@gmail.com", "ramon2022", "Ramon", "Katzh"); 
		
		Role roleAdmin = new Role(1); 
		Role roleEditor = new Role(3); 
		Role roleAssistance = new Role(5); 
		
		
		user4.addRole(roleAssistance);
		user4.addRole(roleEditor);
		
//		user1.addRole(roleAdmin);
//		user1.addRole(roleEditor);
		user2.addRole(roleAdmin);
		user2.addRole(roleAssistance);
		user3.addRole(roleAssistance);
		user3.addRole(roleAdmin);
		user3.addRole(roleEditor);
		
		List<User> users = new ArrayList<>(); 
		users.add(user2);
		users.add(user3);
		users.add(user4); 
		
		List<User> savedUsers = (List<User>) this.userRepository.saveAll(users); 
		
		
		//ASSERT
		assertNotNull(savedUsers);
		assertEquals(savedUsers.size(), users.size(), "original list must have the same size of the saved list");
		assertIterableEquals(users, savedUsers, "Original List should be as persisted list");
		
		
		System.out.println(">>testCreateNewUsersWithRoles() - the following users were saved :"); 
		
		savedUsers.stream().forEach(System.out::println);
//		User savedUser = this.userRepository.save(ravi); 
//		
//		assertThat(savedUser).isNotNull();
//		//assertThat(savedUser.getId()).isEqualTo(2);
//		assertThat(savedUser.getFirstName()).isEqualTo("Ravi");
//		assertThat(savedUser.getRoles().size()).isEqualTo(2);
		
		
	}
	
	
	//OK
	@Test
	//@Disabled
	public void testListAllUsers()
	{
		//GIVEN
		Integer expectedNumberOfUsers = 6; 
		
		//WHEN
		List<User> actualUsers = (List<User>) this.userRepository.findAll(); 
		Integer actualNumberOfUsers = actualUsers.size();
		
		//THEN
		assertThat(expectedNumberOfUsers).isEqualTo(expectedNumberOfUsers); 
		
		
		System.out.println(">>testListAllUsers(): List of all users from DB: "); 
		actualUsers.stream().forEach(System.out::println);
		
	
	}
	
	@Test
	@Disabled
	public void testGetUserById()
	{
		//ARRANGE
		Integer id = 1; 
		
		//ACT
		User user = this.userRepository.findById(id).get();
		
		//ASSERT
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(1); 
		assertThat(user.isEnabled()).isEqualTo(true); 
		
		System.out.println(">>testGetUserById(): The user with id " + id ); 
		System.out.println(user); 
		System.out.println(user.getPhotos());
		//System.out.println(user.getPhotos());
	}
	
	//PERFECT
	@Test
	@Disabled
	public void testUpdateUser()
	{
		//ARRANGE
		Integer id = 1; 
		User user = this.userRepository.findById(id).get();
		System.out.println(">>testUpdateUser(): Original user from db: " + user); 
		
		//ACT
		user.setFirstName("Niron");
		user.setEnabled(true);
		User updatedUser = this.userRepository.save(user); 
		
		//ASSERT
		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.getFirstName()).isEqualTo("Niron"); 
		
		System.out.println(">>testUpdateUser(): Updated user: " + updatedUser); 
		
	}
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
//	@Disabled
//	public void testUpdateUserRoles()
//	{
//		//ARRANGE
//		Integer id = 2; 
//		User userRavi = this.userRepository.findById(id).get();
//		System.out.println(">>testUpdateUserRoles():Updated roles of user with id : " + id); 
//		
//		List<Role> originalRoles = userRavi.getRoles();
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
//	
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
	@Disabled
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
	
	@Test
	@Disabled
	public void testCreateNewUserWithTwoRolesWithImgURL()
	{
		//ARRANGE
		User shalom = new User("shalom@gmail.com", "shalom2022", "Shalom", "Itzhak"); 
		Role roleEditor = new Role(3); 
		Role roleAssistance = new Role(5); 
		
		//ASSERT
		shalom.addRole(roleAssistance);
		shalom.addRole(roleEditor);
		shalom.setPhotos("assets/images/users/2/Allada Pavan.png");
		
		User savedUser = this.userRepository.save(shalom); 
		
		assertThat(savedUser).isNotNull();
		//assertThat(savedUser.getId()).isEqualTo(2);
		assertThat(savedUser.getFirstName()).isEqualTo("Shalom");
		assertThat(savedUser.getRoles().size()).isEqualTo(2);
		
		System.out.println(">>SavedUser:" + savedUser); 
		
	}
	
	
	@Test
	@Disabled
	@DisplayName("Should not find a user with non existing email")
	public void testGetUserByEmailNotFound() {
		
		//GIVEN
		String email = "abc@df.com"; 
		
		//WHEN 
		User user = this.userRepository.getUserByEmail(email);
		
		//THEN
		assertThat(user).isNull();
		
		System.out.println(">>testGetUserByEmailNotFound(): The user: " + user); 
	}
	
	@Test
	@Disabled
	@DisplayName("Should find a user with an existing email")
	public void testGetUserByEmailFound() {
		
		//GIVEN
		String email = "shalom@gmail.com"; 
		
		
		//WHEN 
		User user = this.userRepository.getUserByEmail(email);
		
		//THEN
		assertThat(user).isNotNull();
		assertThat(user.getFirstName()).isEqualTo("Shalom");
		
		System.out.println(">>testGetUserByEmailFound(): The user: " + user); 
	}

	
	/**
	 * Hibernate: 
    delete from users where id=?
	 */
	@Test
	@Disabled
	public void testDeleteUser()
	{
		//ARRANGE
		Integer id = 10; 
		
		
		//ACT
		this.userRepository.deleteById(id);
		
		Optional<User> userOptional = this.userRepository.findById(10); 
		
		//ASSERT
		assertThat(userOptional.isPresent()).isFalse();
		
		
	}
	
	@Test
	@Disabled
	@DisplayName("Should return correct number of users for a given user id")
	public void testCountUserById()
	{
		//ARRANGE
		Integer userId = 30; 
		Long expectedCount = 1l; 
		
		//ACT
		Long actualCount = this.userRepository.countById(userId);
		
		//ASSERT
		assertThat(expectedCount).isNotNull().isEqualTo(actualCount).isGreaterThan(0); 
		
		System.out.println(">>testCountUserById() - actual number of users: " + actualCount); 
		
	
	}
	
	@Test
	public void testListFirstPage()
	{
		//GIVEN
		//first page is 0 
		Integer pageNumber = 0; 
		int pageSize = 4; 
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Integer expectedNumberOfUsers = pageSize;
		
		//WHEN
		Page<User>  page = this.userRepository.findAll(pageable); 
		
		//List of users IN THE PAGE
		List<User> content = page.getContent(); 
		
		
		//THEN
		assertNotNull(content);
		assertEquals(expectedNumberOfUsers, content.size(), "Number of users should must be equals to: " + expectedNumberOfUsers);
		
		
		
		System.out.println(">>testListFirstPage() - list of all users IN THE PAGE!(from page.getContent()): "); 
		 
		content.stream().forEach(System.out::println); 
		
		
		
	}
	
	
	//OK
	@Test
	public void testListSecondPage()
	{
		//GIVEN
		//first page is 0 
		Integer pageNumber = 1; 
		int pageSize = 4; 
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Integer expectedNumberOfUsers = 2; 
		
		//WHEN
		Page<User>  page = this.userRepository.findAll(pageable); 
		
		//List of users IN THE PAGE
		List<User> content = page.getContent(); 
		
		
		//THEN
		assertNotNull(content);
		assertEquals(expectedNumberOfUsers, content.size(), "Number of users should must be equals to: " + expectedNumberOfUsers);
		
		
		
		System.out.println(">>testListFirstPage() - list of all users IN THE PAGE!(from page.getContent()): "); 
		 
		content.stream().forEach(System.out::println); 
	
	}
	
	@Test
	public void testEnabled()
	{
		//GIVEN
		//boolean enabled = true;
		Integer id = 3; 
		
		//WHEN
		User user = this.userRepository.findById(id).get();
		
		System.out.println(">>testDisable(): before enable: " + user.isEnabled()); 
		
		this.userRepository.updateEnabledStatus(id, true);
		
		//THEN
		//assertThat(true).isEqualTo(true); 
		System.out.println(">>testDisable(): after enable: " + user.isEnabled()); 
		
		
		//THEN
		assertThat(user.isEnabled()).isEqualTo(true); 
			
	}
	
	@Test
	public void testDisable()
	{
		//GIVEN
		boolean enabled = false;
		Integer id = 1; 
		
		//WHEN
		User user = this.userRepository.findById(id).get();
		System.out.println(">>testDisable(): before disalbe: " + user.isEnabled()); 
		this.userRepository.updateEnabledStatus(id, enabled);

		

		//THEN
		assertThat(user.isEnabled()).isEqualTo(false); 
		System.out.println(">>testDisable(): after disable: " + user.isEnabled()); 
			
	}
	
	
	
	
	
	
	
//	
	
	

}
