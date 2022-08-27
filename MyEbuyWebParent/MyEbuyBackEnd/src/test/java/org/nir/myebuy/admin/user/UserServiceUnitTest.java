package org.nir.myebuy.admin.user;

import static org.junit.jupiter.api.Assertions.assertAll;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nir.myebuy.common.entity.User;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@Disabled
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceUnitTest {
	
	private List<User> users; 
	
	@MockBean
	private UserRepository userRepository; 
	
	@MockBean
	private Sort sort;
	
	@MockBean
	private Pageable pageable;
	
	@InjectMocks
	private UserService userService;
	
	
	
	
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
	

	@DisplayName("thes inject service and it's dependencies")
	@Test
	public void testInject()
	{
		assertAll("should inject repo into service", 
				() ->assertNotNull(this.userRepository),
				() ->assertNotNull(this.sort),
				() ->assertNotNull(this.pageable),
				() ->assertNotNull(this.userService));
	}
	
	
	//OK
	@DisplayName("test shouldReturnTrueWhenEmailIsNotUnique false")
	@Test
	public void shouldReturnFalseWhenEmailIsUnique()
	{
		//PREPARE THE MOCK - DATA - INPUT AND OUPUT
		//GIVEN(non existing id and non existing email  - no violation
		Integer userId = null; 
		String userEmail  =  "tommy@gmail.com"; 
		
		//(for the non existing email then return null for non existing user in the DB
		Mockito.when(this.userRepository.getUserByEmail(userEmail)).thenReturn(null); 
		
		//WHEN
		boolean result = this.userService.isUniqueEmailViolated(userId, userEmail);
		
		
		//THEN
		assertFalse(result);
		
		System.out.println(">>testIsUniqueEmailViolatedFalse() Violated : " + result);		
			
	}
	
	//OK
		@DisplayName("test shouldReturnTrueWhenEmailIsNotUnique true")
		@Test
		public void shouldReturnTrueWhenEmailIsNotUnique()
		{
			//PREPARE THE MOCK - DATA - INPUT AND OUPUT
			//GIVEN(non existing id and  existing email -   violation
			Integer userId = 1;  
			String userEmail  =  "niritzhak10@gmail.com"; 
			
			//Set the email to existing one 
			User user = new User(); 
			user.setEmail(userEmail);
			
			
			//(for the non existing email then return null for non existing user in the DB
			Mockito.when(this.userRepository.getUserByEmail(userEmail)).thenReturn(user); 
			
			//WHEN
			boolean result = this.userService.isUniqueEmailViolated(userId, userEmail);
			
			
			//THEN
			assertTrue(result);
			
			System.out.println(">>testIsUniqueEmailViolatedTrue() Violated : " + result);		
				
		}
		
		
		
		
		
		
		
		/*****************************************************************8
		 * 			MY METHODS - OK - 
		 ******************************************************************/
		
		//MY METHOD AND MY TEST  - GREAT!!!
		/**
		 * >>UserServcie - getEmailById() - repo return email: NirItzhak10@Gmail.Com
			>>testGetEmail - email from the service(converted to lower case): niritzhak10@gmail.com
		 */
		@DisplayName("testGetEmail - convert to lower case success")
		@Test
		public void testGetEmail()
		{
			//GIVEN - PRE
			Integer id = 1; 
			String email = "NirItzhak10@Gmail.Com"; 
			String expectedEmail = email.toLowerCase();
			
			//Prepare the Mock to return the above email 
			Mockito.when(this.userRepository.getEmailById(id)).thenReturn(email);  
			
			
			//WHEN
			String actualEmail = this.userService.getEmailById(id); 
			
			
			//THEN
			assertEquals(expectedEmail, actualEmail);
			
			
			System.out.println(">>testGetEmail - email from the service(converted to lower case): " + actualEmail);
			
		}
		
		/*
		 * public Page<User> listByPage(Integer pageNum, String sortField, String sortDir)
	{
		//Create a Sort object 
		Sort  sort = Sort.by(sortField); 
		
		//Configure the sort direction based on the sortDir paramter
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		//Create Pageable of size 
		Pageable pageable = PageRequest.of(pageNum - 1, USERS_PER_PAGE, sort); 
		
		return this.userRepository.findAll(pageable);
	}
		 */
		@DisplayName("Test listByPage With Sorting")
		@Test
		public void testListByPageWithSorting()
		{
			//GIVEN
			
			
			//WHEN
			
			
			
		}
		
		

}
