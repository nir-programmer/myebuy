package org.nir.myebuy.api.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.nir.myebuy.api.role.RoleModelAssembler;
import org.nir.myebuy.api.role.RoleService;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * NOTE: <spring-boot-starter-test> :For Unit testing and Integeration testing of Rest Service
 * Mockito: Mock Object on the UserService dependency 
 * 
 * @WebMvcTest(Controller class udner thes) : 
 *  Provides by SpringBootTest - Provide the MockMvc object - to perform Http Requests and verify responses
 *
 *
 */

//CHECK FOR SECURITY??
@WebMvcTest(UserController.class)
public class UserRestControllerUnitTest {

	
	//To perform Http requests and verify the response
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private ObjectMapper mapper ;
	
	
	
	//Mock the dependencies 
	@MockBean
	private UserService userService; 
	
	
	@MockBean
	private RoleService roleService;
	
	@MockBean
	private UserModelAssembler userModelAssembler; 
	
	@MockBean
	private RoleModelAssembler roleModelAssembler;
	////////////
	
	
	//Security : CHECK!	
//	@MockBean
//	private UserRepository userRepository; 
	
	
	@DisplayName("Test list all users")
	@Test
	public void testListAllUsers() throws Exception
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
		
		List<User> users = new ArrayList<>(); 
		users.add(user1);
		users.add(user2);
		
		Mockito.when(this.userService.getAllUsers()).thenReturn(users);
		
		
		//Prepare the web 
		String url = "/users";
		
		
		//assertEquals(, null);
		//WHEN
		MvcResult mvcResult = this.mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
	
		System.out.println(mvcResult.getResponse().getContentAsString()); 
		//THE
		//Verify the response
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();    
		
		System.out.println(">>testListAll() - JSON response: " + actualJsonResponse);
	}
	
}
