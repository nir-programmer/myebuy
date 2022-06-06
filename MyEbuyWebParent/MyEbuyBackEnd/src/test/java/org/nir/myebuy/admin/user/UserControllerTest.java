package org.nir.myebuy.admin.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest 
{
	/*
	 * CHAD ADDED THIS WHEN CREATE THE TEST FOR POST A NEW USER
	 * 
	 * This obect is used to make mock servlet request
	 */
	private static MockHttpServletRequest request;
	
	
	@BeforeAll
	public static void beforeAll()
	{
		request = new MockHttpServletRequest();
		request.setParameter("firstName", "Nir");
		request.setParameter("lastName", "Itzhak");
		request.setParameter("email", "niritzhak10@gmail.com");
		request.setParameter("password", "YYY");
	
	}
	
	private List<User> users ;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private MockMvc mockMvc; 
	
	//Mock the UserService
	@Mock
	private UserService userServiceMock;
	
	
	
	@BeforeEach
	public void beforeEach()
	{
		//GIVEN
				//Prepare the mock  - input - output data 
				User user1 = new User();
				user1.setId(1);
				user1.setFirstName("Nir");
				user1.setLastName("Ithzak");
				user1.setEmail("niron@gmail.com");
				user1.setPassword("AAA");
				user1.setEnabled(true); 
				
				User user2 = new User();
				user2.setId(2);
				user2.setFirstName("Chad");
				user2.setLastName("Darby");
				user2.setEmail("chad@gmail.com");
				user2.setPassword("BBB");
				user2.setEnabled(false); 
				
				this.users = new ArrayList<>(); 
				users.add(user1);
				users.add(user2);
				
			this.userRepository.saveAll(users);
			
			System.out.println("=============================beforeEach() save all users==========\n\n"); 
			
	}
	
	
	@AfterEach
	public void afterEach()
	{
		this.userRepository.deleteAll();
		
		System.out.println("=============================afterEach() delete all users==========\n\n"); 

		
	}
	
	
	@DisplayName("Test Authowried")
	@Test
	public void testAutowried()
	{
		assertAll("assert autowhoried",  
				() ->assertNotNull(this.jdbcTemplate),
				() -> assertNotNull(this.userServiceMock),
				() -> assertNotNull(this.mockMvc),
				() ->assertNotNull(this.userRepository)
				);
		System.out.println(">>List of all students:");
		this.users.stream().forEach(System.out::println);
		
	}
	
	@DisplayName("Test Http GET Request for users endpoint return correct view name")
	@Test
	public void testGetUsersReturnCorrectViewName() throws Exception
	{
		//GIVEN
		User user1 = new User();
		user1.setId(1);
		user1.setFirstName("Nir");
		user1.setLastName("Ithzak");
		user1.setEmail("niron@gmail.com");
		user1.setPassword("AAA");
		user1.setEnabled(true); 
		
		User user2 = new User();
		user2.setId(2);
		user2.setFirstName("Chad");
		user2.setLastName("Darby");
		user2.setEmail("chad@gmail.com");
		user2.setPassword("BBB");
		user2.setEnabled(false); 
		
		List<User> list = new ArrayList<>(); 
		list.add(user1);
		list.add(user2);

		
		//PREPARE THE MOCK
		when(this.userServiceMock.listAll()).thenReturn(list);
		
		//SIMPLE ASSERT TO VERIFY THE MOCK - NOT WEB YET!
		assertIterableEquals(list, this.userServiceMock.listAll());
		
		System.out.println(">>gestGetUsers() - OK - the UserServiceMock return the list:");
		this.userServiceMock.listAll().stream().forEach(System.out::println);
		
		
		//
		
		//WHEN
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(status().isOk()).andReturn();
		
		
		
		ModelAndView mav = mvcResult.getModelAndView();  
		
		
		///THEN - OK!!! 
		ModelAndViewAssert.assertViewName(mav, "users");
	}
	
	
	/**
	 * 
	 * NOTE: The my POST END POINT REDIRECT TO USERS - so status code is 302
	 */
	@Test
	@DisplayName("Test POST for adding a new User")
	public void testCreateNewUserWithPostSuccess() throws Exception
	{
		MvcResult mvcResult = this.mockMvc.perform(post("/users/save").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", request.getParameterValues("firstName"))
				.param("lastName", request.getParameterValues("lastName"))
				.param("email", request.getParameterValues("email"))
				.param("password", request.getParameterValues("password"))
				) 
				.andExpect(status().is3xxRedirection()).andReturn();
		
		
		ModelAndView mav = mvcResult.getModelAndView();  
		
		
		
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/users");
		
	}

}
