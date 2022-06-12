package org.nir.myebuy.admin.user.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		
		this.jdbcTemplate.execute("insert into users(first_name, last_name, email,enabled,password ) "
				+ "values ('Nir', 'Ithzak', 'nir@gmail.com', true, 'superduper1010')");
		
		this.jdbcTemplate.execute("insert into users(first_name, last_name, email,enabled,password ) "
				+ "values ('Niron', 'Ithzak', 'nirony@gmail.com', true, 'superduper1')");
		
		this.jdbcTemplate.execute("insert into users(first_name, last_name, email,enabled,password ) "
				+ "values ('Shalom', 'Ithzak', 'shalom@gmail.com', true, 'superduper10')");
		
		this.jdbcTemplate.execute("insert into users(first_name, last_name, email,enabled,password ) "	
				+ "values ('XXXXX', 'YYYY', 'xxxxxx@gmail.com', false, 'superduper100')");
		
		this.jdbcTemplate.execute("insert into users(first_name, last_name, email,enabled,password ) "
				+ "values ('Chad', 'Darby', 'chad@gmail.com', true, 'superduper1000000')");
		
		
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
		Integer expectedNumberOfUsers = 6; 
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
		long expectedCount = 6; 
		
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
		assertEquals(6, this.userRepository.count());
		
		//WHEN
		User savedUser = this.userRepository.save(user); 
		
		//THEN
		assertNotNull(savedUser);
		assertEquals(7, this.userRepository.count());
//		
//		List<User> users = (List<User>) this.userRepository.findAll();
//		
//		System.out.println(">>testCreateNewUser() - list of users after creating a user:"); 
//		
//		users.stream().forEach(System.out::println);
			
	}
	
	
	//Should I mock the Pageable ??? 
	@DisplayName("Test listFirstPage()")
	@Test
	public void testListFirstPage()
	{
		//GIVEN
		int pageNumber = 0 ; 
		int pageSize = 4; 
		
		assertEquals(6, ((Collection<User>)this.userRepository.findAll()).size());
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize); 
		
		//WHEN
		Page<User> page = this.userRepository.findAll(pageable); 
		
		
		//THEN
		List<User> users = page.getContent();
		assertEquals(users.size(), pageSize);
		
		
		
		users.forEach(System.out::println);
		
		
	}
	
	
	
	//MySQL: mysql> SELECT * FROM users WHERE first_name LIKE '%bruce%';
	@DisplayName("Test SearchUsers()")
	@Test
	public void testSearchUsersFound()
	{
		//GIVEN
		String keyword = "Nir"; 
		
		
		int pageSize = 4; 
		int pageNumber = 0; 
		Pageable pageable = PageRequest.of(pageNumber, pageSize); 
	
		
		//WHEN
		Page<User> page = this.userRepository.findAll(keyword,pageable);
		
		
		//WHEN
		List<User> users = page.getContent();  
		
		//OK - I HAVE IN THE LIST FIRST ANME: Nir, Niron
		assertNotNull(users);
		assertTrue(users.size() > 0 );
		
		users.forEach(System.out::println);

	
	}
//	
//	
	
	
	

}
