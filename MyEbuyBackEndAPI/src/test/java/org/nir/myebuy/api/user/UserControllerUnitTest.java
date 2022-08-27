package org.nir.myebuy.api.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nir.myebuy.api.role.RoleModelAssembler;
import org.nir.myebuy.api.role.RoleService;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@Disabled
@WebMvcTest(UserController.class)
public class UserControllerUnitTest
{
	private static String USER_ENDPOINT = "/users";

	private static final Logger logger = Logger.getLogger(RestUsersTests.class);
	
	
//	@MockBean
//    private RestTemplate restTemplate;
//	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService; 
	
	@MockBean
	private RoleService roleService; 
	
	@MockBean
	private UserModelAssembler userModelAssembler;

	@MockBean
	private RoleModelAssembler roleModelAssembler; 
	
	@Autowired 
	private ObjectMapper mapper;
	
	//NOTE: This is not a dependency of the UserControlller under test - But I need this for createing the mock
//	@Autowired
//	private UserRepository userRepository;
	
	
	//CHECK FOR UserReporisory - for security  - later ...
	


	@Test
	@DisplayName("Test all autowired and mocks are injected")
	public void testSmoke() {
		
		assertThat(this.mapper).isNotNull();
		assertAll("all autowired and mocks should not be null", 
				() -> assertNotNull(this.mapper),
				() -> assertNotNull(this.userModelAssembler),
				() -> assertNotNull(this.roleModelAssembler),
				() -> assertNotNull(this.userService),
				() -> assertNotNull(this.mockMvc)
				//() -> assertNotNull(this.roleRepository)
				);
	}
	
	/*
	 * NOTE: Since the method under test - invoke the repo.findAll() - I need to create a mock for this object and mock the data
	 */
	@Test
	@DisplayName("Test should return correct list of users")
	public void testGetAllUsers() throws Exception
	{
		//GIVEN
		String url  = "/users";
		//Prepare the mock for the service and mock the data 
		User user1 = new User("niritzhak10@gmail.com", "superduper100", "Nir", "Itzhak"); 
		User user2 = new User("niron10@gmail.com", "bogotobogo100", "Niron", "Itzhak"); 
		
		//Role adminRole = this.entityManager.find(Role.class, 1); 
		
		//ACT
		//nir.addRole(adminRole);
		List<User> users = new ArrayList<>(); 
		
		//Mock the ResponseEntity<CollectionModel<UserModel>>
//		CollectionModel<UserModel> collectionModel = 
//		ResponseEntity<CollectionModel<UserModel>> responseEntity =  new ResponseEntity<> (
//				this.userModelAssembler.toCollectionModel(users),HttpStatus.OK);
		
	
		Mockito.when(this.userService.getAllUsers()).thenReturn(users);
		//Mockito.when(this.userModelAssembler.toCollectionModel(users)).thenReturn(null)
		
		
		
		
		
		
		//VERIFY the response for the request 
		//this.mockMvc.perform(MockMvcRequestBuilders.get(url));
		MvcResult mvcResult =  this.mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		
		//Verify the JSON string response 
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		
		
		System.out.println(">>testGetAllUsers() - the JSON response: "); 
		System.out.println(actualJsonResponse);
		
	}
	
	
	/**
	 * public class AlbumModelAssembler 
		  extends RepresentationModelAssemblerSupport<AlbumEntity, AlbumModel> 
		{
		  @Override
		  public CollectionModel<AlbumModel> toCollectionModel(Iterable<? extends AlbumEntity> entities) 
		  {
		    CollectionModel<AlbumModel> actorModels = super.toCollectionModel(entities);
		  }
		}
	 */
	

}
