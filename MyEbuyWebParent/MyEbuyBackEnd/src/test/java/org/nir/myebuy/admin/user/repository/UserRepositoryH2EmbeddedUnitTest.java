package org.nir.myebuy.admin.user.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.admin.user.UserRepository;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

//I WROTE THIS CLASS
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryH2EmbeddedUnitTest 
{
	//for perform db operations that does not involve User entities(for the roles) 
	@Autowired
	private TestEntityManager entityManager; 
	
	
	@Autowired
	private UserRepository userRepository;  
	
	private List<User> users; 
	
	@BeforeEach
	public void beforeEach()
	{
		User user1 = User.builder().id(1).firstName("Nir").lastName("Ithzak").email("niritzhak10@gmail.com").password("aaa").build();  
		User user2 = User.builder().id(2).firstName("Niron").lastName("Ithzakon").email("niritzhakon10@gmail.com").password("bbb").build();  
		
		this.users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
//		this.entityManager.persist(user1);
//		this.entityManager.persist(user2);
		
		this.userRepository.saveAll(this.users);
		System.out.println("beforeEach() save all users");

		
	}
	
	@AfterEach
	public void afterEach()
	{
		//this.entityManager.detach(entityManager);
		this.userRepository.deleteAll();
		System.out.println("afterEach() delete all users");
	}
	
	
	@DisplayName("Test list all users")
	@Test
	public void testListAllUsers()
	{
		//GIVEN
		List<User> users = (List<User>) this.userRepository.findAll(); 
		
		//THEN
		assertAll("list of users assertions",
				() -> assertNotNull(users),
				() -> assertEquals(2, users.size())
				);
		
		
		System.out.println(">>testListAllUsers() - list of all users:"); 
		users.stream().forEach(System.out::println);
	}
	
	/**OK!!
	 * org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; 
	 * constraint ["PUBLIC.UK_6DOTKOTT2KJSP8VW4D0M25FB7_INDEX_4 ON PUBLIC.USERS(EMAIL) VALUES 1"; SQL statement:
	 * insert into users (id, email, enabled, first_name, last_name, password, photos) values (default, ?, ?, ?, ?, ?, ?) [23505-200]];
	 *  nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement

	 */
	@Test
	@DisplayName("Test Create User")
	public void testCreateUser()
	{
		User user = User.builder().id(3).firstName("Chad").lastName("Darby").email("chad@gmail.com").password("ccc").build();  
		
		User savedUser =this.userRepository.save(user);
		
		assertNotNull(savedUser);
		assertEquals(3, this.userRepository.count());
		
		System.out.println(">>testCreateUser - list of users after create: "); 
		
		Iterable<User> iterable = this.userRepository.findAll();
		
		iterable.forEach(System.out::println);
		
		
	}
	
	
	//MY METHOD AND TEST - WORKS
	@DisplayName("Test get email by id found")
	@Test
	public void testGetEmailByIdFound()
	{
		//GIVEN
		String expectedEmail = "niritzhak10@gmail.com";
		Integer id = 1; 
		
		//WHEN
		String actualEmail = this.userRepository.getEmailById(id); 
		
		
		//THEN
		assertEquals(expectedEmail, actualEmail);
		
		
		System.out.println(">>testGetEmailIdNotFound() - email: " + actualEmail); 
			
	}
	
	//GREAT
	@DisplayName("Test get email by id found")
	@Test
	public void testGetEmailByIdNotFound()
	{
		//GIVEN
		String expectedEmail = null; 
		Integer id = 3; 
		
		//WHEN
		String actualEmail = this.userRepository.getEmailById(id); 
		
		
		//THEN
		assertEquals(expectedEmail, actualEmail);
		
		
		System.out.println(">>testGetEmailIdNotFound() - email: " + actualEmail); 
			
	}
	
	
	

}
