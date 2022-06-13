package org.nir.myebuy.api.user.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.api.user.UserRepository;
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

//@SpringBootTest -> DONT ADD THIS - TEST ONLY DAO LAYER! 
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryH2EmbeddedTest 
{
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
		
		
		assertEquals(2, users.size());
		System.out.println(">>testListAllUsers() - list of all users:"); 
		users.stream().forEach(System.out::println);
	}
	
	
	@DisplayName("Test findByFirstNameContaining() - found")
	@Test
	public void testFindByFirstNameContainingFound()
	{
		//GIVEN
		String firstName = "Nir"; 
		Integer pageNumber = 1; 
		//NOTE: IF THERE ARE LESS ITEMS THAN THE DEFINED PAGE SIZE - IT WILL RETURN NULL
		int pageSize = 1;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		//Integer expectedNumberOfUsers = 2; 
		
	
		//WHEN
		Page<User> page = this.userRepository.findByFirstNameContaining(firstName, pageable);
		
		
		
		//THEN
		List<User> users = page.getContent();
		assertNotNull(users);
		assertEquals(1, users.size());
		
	}

}
