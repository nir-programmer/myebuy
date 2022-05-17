package org.nir.myebuy.api.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.api.role.RoleController;
import org.nir.myebuy.api.role.RoleRepository;
import org.nir.myebuy.api.role.RoleService;
import org.nir.myebuy.api.user.response.UserEmbedded;
import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RoleController.class)
public class RestRolesTests {
	private static String USER_ENDPOINT = "/roles";

	private static final Logger logger = Logger.getLogger(RestRolesTests.class);
	
	
	@MockBean
    private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RoleService roleService	; 
	
	
	@Autowired 
	private ObjectMapper mapper;
	
//	@Autowired
//    private TestRestTemplate template;

//	@MockBean
//	private RoleRepository roleRepository;
	
	@MockBean  
	private RoleRepository roleRepository;

	@Test
	public void testSmoke() {
		assertThat(this.mapper).isNotNull();
	}
	
	@Test
	public void testGetAllRoles()
	{
		//GIVEN
		Role role1 = new Role(1, "Admin", "manage everything"); 
		Role role2 = new Role(1, "Shipper",  "manage shipings"); 
		
		List<Role> roles = new ArrayList<>();
		
		roles.add(role1);
		roles.add(role2); 
		
		UserEmbedded embedded = new UserEmbedded();
		
		
		
		
		
		
	}


}
