package org.nir.myebuy.admin.user.repository;


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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

	
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
//@Disabled
public class UserRepositoryH2StandAloneUnitTest
{
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private TestEntityManager entityManager; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	/**
	 * ------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | int          | NO   | PRI | NULL    | auto_increment |
| email      | varchar(128) | NO   | UNI | NULL    |                |
| enabled    | bit(1)       | NO   |     | NULL    |                |
| first_name | varchar(45)  | NO   |     | NULL    |                |
| last_name  | varchar(45)  | NO   |     | NULL    |                |
| password   | varchar(64)  | NO   |     | NULL    |                |
| photos     | varchar(64)  | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+

	 */
	@BeforeEach
	public void beforeEach()
	{
		this.jdbcTemplate.execute("insert into users(first_name, last_name, email,enabled,password ) "
											+ "values ('Eric', 'Roby', 'eric.roby@gmail.com', true, 'superduper100')");
		
	}
	
	@AfterEach
	public void afterEach()
	{
		this.jdbcTemplate.execute("DELETE FROM users_roles");
		this.jdbcTemplate.execute("DELETE FROM users"); 
		this.jdbcTemplate.execute("DELETE FROM roles");
	}
	
	
	
	
	////////////////////////////////////////////////////////////////////////
	@DisplayName("Test autowired")
	@Test
	public void testAutowired()
	{
		assertNotNull(this.entityManager);
		assertNotNull(this.userRepository);
		assertNotNull(this.jdbcTemplate);	
	}
	
	
	@DisplayName("Test list all users")
	@Test
	public void testFindAll()
	{
		//GIVEN
		Integer expectedNumberOfUsers = 1; 
		List<User> users = new ArrayList<>();
		
		//WHEN
		users = (List<User>) this.userRepository.findAll();
		Integer actualNumberOfUsers = users.size();
		
		//THEN
		assertEquals(expectedNumberOfUsers, actualNumberOfUsers);
		
		System.out.println(">>testFindAll() - list of users:"); 
		
		users.stream().forEach(System.out::println);
	}
	
	
	@DisplayName("Test count")
	@Test
	public void testCount()
	{
		//GIVEN
		long expectedCount = 1; 
		
		//WHEN
		long actualCount = this.userRepository.count();
		
		//THEN
		assertEquals(expectedCount, actualCount);
	}
	
	@DisplayName("Test create new User")
	@Test
	public void testCreateNewUser()
	{
		//GIVEN
		//assertEquals(this., null);
		User user = new User("niritzhak10@gmail.com","superduper100",  "Nir", "Ithzak" );
		assertEquals(1, this.userRepository.count());
		
		//WHEN
		User savedUser = this.userRepository.save(user); 
		
		//THEN
		assertNotNull(savedUser);
		assertEquals(2, this.userRepository.count());
		
		List<User> users = (List<User>) this.userRepository.findAll();
		
		System.out.println(">>testCreateNewUser() - list of users after creating a user:"); 
		
		users.stream().forEach(System.out::println);
			
	}
	
	
	
	
	

}
