package org.nir.myebuy.api.user;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
//Test whole app
@SpringBootTest
//To use the MockMVC object 
@AutoConfigureMockMvc
@Disabled
public class UserControllerIntegrationTest 
{
	@Autowired
	private MockMvc mockMvc; 
	
	
	@Autowired
	private ObjectMapper mapper;
	
	//To Verify the results in the DB
	@Autowired
	private UserRepository userRepository;
	
	
	
	@BeforeEach
	public void beforeEach()
	{
		User user1 = new User("niritzhak10@gmail.com", "superduper100", "Nir", "Itzhak"); 
		User user2 = new User("niron10@gmail.com", "bogotobogo100", "Niron", "Itzhak"); 
		
		this.userRepository.save(user1);
		this.userRepository.save(user2); 
		
		
	}
	
	
	@Test
	@DisplayName("Test all autowired")
	public void testSmoke()
	{
		assertAll("all autowired should not be null", 
				() -> assertNotNull(this.mapper),
				() -> assertNotNull(this.userRepository),
				() ->assertNotNull(this.mockMvc)
				);
	}

	
	//GREAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Test
	@DisplayName("Test getAllUsers")
	public void testGetAllUsers() throws Exception
	{
		//GIVEN
		Integer expectedSizeOfJsonArrayInResponse = 2; 
		String url  = "/users";

	
	MvcResult mvcResult = this.mockMvc.perform(get(url))
		.andExpect(status().isOk())
		//.andExpect(content().contentType(MediaType.APPLICATION_JSON))			
		.andExpect(content().contentType("application/hal+json"))
		.andExpect(jsonPath("$._embedded.users",hasSize(expectedSizeOfJsonArrayInResponse)))
		.andDo(print())
		.andReturn();
	
	String actualJson = mvcResult.getResponse().getContentAsString();
	
	System.out.println(actualJson);
	
	//System.out.println(jsonPath("$._embedded.users", 2));
	
  
	  
		
	  
	  
		
		
		
	}
}
