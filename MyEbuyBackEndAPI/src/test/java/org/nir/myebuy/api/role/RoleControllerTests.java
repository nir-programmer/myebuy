package org.nir.myebuy.api.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.nir.myebuy.api.role.response.GetResponseRoles;
import org.nir.myebuy.api.role.response.RoleEmbedded;
import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RoleController.class)
public class RoleControllerTests {
	
	private static String ROLES_ENDPOINT = "/roles";

	private static final Logger logger = Logger.getLogger(RoleControllerTests.class);
	
	
	@MockBean
    private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RoleService roleService ; 
	
	
	@Autowired 
	private ObjectMapper mapper;
	
	@Mock
    private TestRestTemplate template;

//	@MockBean
//	private UserRepository userRepository;
	
	
	@MockBean  
	private RoleRepository roleRepository;

	
	
	
	private static List<Role> roles ; 
	private static RoleEmbedded roleEmbedded; 
	private static GetResponseRoles getResponseRoles;
	
//	@BeforeAll
//	static void init() {
//		roles = new ArrayList<>(); 
//		
//		Role role1 = new Role(1, "Admin", "manage everything");
//		Role role2 = new Role(2, "Shipper", "manage shippings");
//		
//		roles.add(role1);
//		roles.add(role2);
//		
//		roleEmbedded.setRoles(roles);
//		
//		
//	}
//	
	@Test
	public void testSmoke() {
		assertThat(this.mapper).isNotNull();
	}

	

}
