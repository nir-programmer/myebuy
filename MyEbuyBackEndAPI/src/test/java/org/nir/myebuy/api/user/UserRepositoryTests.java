package org.nir.myebuy.api.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
//For testing with the real DB (instead H2 in memory used by Spring) 
@AutoConfigureTestDatabase(replace = Replace.NONE)
//To update the real db
@Rollback(false)
@Disabled
public class UserRepositoryTests 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager; 
	

	@Test
	public void testSmoke()
	{
		assertThat(this.userRepository).isNotNull();
		assertThat(this.entityManager).isNotNull();
	}
	
	//OK
	@Test
	@Disabled
	public void testCreateNewUserWithOneRole()
	{
		//ARRANGE
		User nir = new User("niritzhak10@gmail.com", "superduper100", "Nir", "Itzhak"); 
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
	@DisplayName("Should return correct list of users from db")
	//@Disabled
	public void testListAllUsers()
	{
		//GIVEN
		int expectedUsers = 1; 
		
		//WHEN
		List<User> users = (List<User>) this.userRepository.findAll();
		int actualUsers = users.size();
		
		//THEN
		assertThat(expectedUsers).isEqualTo(expectedUsers);
		
		System.out.println(">>testListAllUsers() - list of all users"); 
		users.stream().forEach(System.out::println);
	
	}
	
	@Test
	//@Disabled
	public void testCreateNewUsersWithRoles()
	{
		//ARRANGE
		//User user1 = User.builder().("chad@gmail.com").password("chad2022").firstName("Chad").lastName("Darby").build();
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

		
		
	}
	
	
	//OK
	@Test
	@Disabled
	public void testCreateNewUserWithTwoRoles()
	{
		//ARRANGE
		User ravi = new User("ravi@gmail.com", "ravi2022", "Ravi", "Kumar"); 
		Role roleEditor = new Role(3); 
		Role roleAssistance = new Role(5); 
		
		//ASSERT
		ravi.addRole(roleAssistance);
		ravi.addRole(roleEditor);
		
		User savedUser = this.userRepository.save(ravi); 
		
		assertThat(savedUser).isNotNull();
		//assertThat(savedUser.getId()).isEqualTo(2);
		assertThat(savedUser.getFirstName()).isEqualTo("Ravi");
		assertThat(savedUser.getRoles().size()).isEqualTo(2);
		
		
	}
	
	//GREAT
	@Test
	@Disabled
	public void testGetUserByEmail()
	{
		//String email =  "niritzhak10@gmail.com";
		String email = "ravi@gmail.com";
		User user = userRepository.getUserByEmail(email);
		
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(2);
		
		System.out.println(">>testGetUserByEmail(): " + user);
		

	}
	
	@Test
	public void testGetUser()
	{
		//ARRANGE
		Integer id =2; 
		
		
		//ACT 
		Optional<User> userOptional = this.userRepository.findById(id);
		User user = userOptional.get();
		
		//ASSERT
		assertThat(userOptional.isPresent()).isTrue();
		assertThat(user).isNotNull();
		assertThat(user.getRoles().size()).isEqualTo(2);
		
		System.out.println(">>testGetUser(): user: " + user);
		
	}
	
	//GREAT
	@Disabled
	@Test
	@DisplayName("Should add new role to the user")
	public void testUpdateRolesOfUser()
	{
		//ARRANGE
		Integer userId = 2; 
		Integer roleId = 1; 
		Integer expectedNumberOfRoles = 3; 
		Role role = this.entityManager.find(Role.class,roleId );
		User user = this.userRepository.findById(userId).get();
		List<Role> roles = user.getRoles();
		roles = user.getRoles();
		System.out.println(">>testUpdateRolesOfUser() -roles of Original user:");
		roles.forEach(System.out::println);
		
		//ACT
		user.addRole(role);
		User updatedUser = this.userRepository.save(user);
		
		//ASSERT
		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.getRoles().size()).isEqualTo(expectedNumberOfRoles);
		
		System.out.println(">>testUpdateRolesOfUser() -roles of updated user");
		roles = updatedUser.getRoles();
		roles.forEach(System.out::println);
		
	}
	//H2 CODE:     SELECT COUNT(*) from users;
	@Test
	public void testCount()
	{
		//GIVEN
		long expectedNumberOfUsers = 3; 
		
		//WHEN
		//this.userRepository.deleteAll();
		
		long actualNumberOfUsers = this.userRepository.count();
		
		
		//THEN
		assertThat(actualNumberOfUsers).isEqualTo(expectedNumberOfUsers);
	
	}
	
	
	/**
	 * IMPORTANT : CHECK IF IS POSSIBLE TO DELETE USERS - WHICH ARE NOT REFERED BY ROLES(
	 * NO BI-DIRECTIONAL ! THE ROLES DONT REFER TO USERS - SHOULD NOT BE A PROBLEM
	 */
	@Test
	public void testDeleteAll()
	{
		//GIVEN
		long expectedNumberOfUsersAfterRemoveAll = 0; 
		
		//WHEN
		this.userRepository.deleteAll();
		
		long actualNumberOfUsersAfterRemoveAll = this.userRepository.count();
		
		
		//THEN
		assertThat(actualNumberOfUsersAfterRemoveAll).isEqualTo(expectedNumberOfUsersAfterRemoveAll);
		
	}
	
	@Test
	public void testListFirstPage()
	{
		//GIVEN
		Integer pageNum = 0 ; 
		Integer pageSize = 4; 
		
		Pageable pageable = PageRequest.of(pageNum, pageSize); 
		
		Page<User> pageUsers  = this.userRepository.findAll(pageable); 
		
		//WHEN
		List<User> listUsers = pageUsers.getContent();
		
	
		//THEN
		assertEquals(4, listUsers.size()); 
		
		
		System.out.println(">>testListFirstPage() - content of first page:"); 
		
		listUsers.stream().forEach(System.out::println);

	}

}
