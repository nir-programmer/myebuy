package org.nir.myebuy.api.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

//import org.jboss.logging.Logger;
//import org.junit.jupiter.api.Test;
//import org.nir.ecommerce.entity.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.nir.myebuy.api.role.RoleRepository;
import org.nir.myebuy.api.user.response.UserEmbedded;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(UserController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestUsersTests 
{
//	private static String USER_ENDPOINT = "http://localhost:8083/MyEbuyAdminAPI/api/users";
	
	private static String USER_ENDPOINT = "/users";

	private static final Logger logger = Logger.getLogger(RestUsersTests.class);
	
	
	@MockBean
    private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService; 
	
	
	@Autowired 
	private ObjectMapper mapper;
	
//	@Autowired
//    private TestRestTemplate template;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean  
	private RoleRepository roleRepository;

	@Test
	public void testSmoke() {
		assertThat(this.mapper).isNotNull();
	}

	
	
	/**
	 * java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" 
	 * because the return value of 
	 * "org.springframework.web.client.RestTemplate.exchange(String, org.springframework.http.HttpMethod, org.springframework.http.HttpEntity,
	 *  org.springframework.core.ParameterizedTypeReference, Object[])" 
	 *  is null

	 * @throws Exception
	 */
    @Test 
    public void testGetUsers() throws Exception
    {
    	//ARRANGE 
    	//MOCKING data 
    	List<User> users = new ArrayList<User>(); 
    	User user1 = new User(1, "nir@gmail.com", "", "Nir", "Itzhak","", false);
    	User user2 = new User(2, "niron@gmail.com", "", "Niron", "Itzhak","", true);
    	User user3 = new User(3, "shalom@gmail.com", "", "Shalom", "Itzhak","", true);
    	
    	users.add(user1);
    	users.add(user2);
    	users.add(user3);
    	
    	GetResponseUser getResponseUser = new GetResponseUser();
    	
    	UserEmbedded embedded = new UserEmbedded();
    	
    	embedded.setUsers(users);
    	
    	getResponseUser.setEmbedded(embedded);
    	
    	System.out.println("Expected: GetResponseUser stringfiy to JSON:\n" +  getResponseUser); 
    	
    	String jsonResponseExpected = this.mapper.writeValueAsString(getResponseUser);
    	System.out.println("jsonResonse: \n" + jsonResponseExpected);
    	
    	
//    	Embedded actualResponse = this.restTemplate
//                .exchange(USER_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<GetResponseUser>() {
//                }).getBody().getEmbedded();
    	//Convert the List<User> to a JSON stri
    	
    	//System.out.println("actualResponse: " + actualResponse);
    	//String jsonResponseExpected = this.mapper.writeValueAsString(users);
    	
    	//ACT
    	
    	//Mock the response data created by the Repo 
    	//Mockito.when(this.userRepository.findAll()).thenReturn(users);
    	
    	//Mock the request  - OK
    	//OK - GET the response (raw)
    	//MvcResult mvcResult = this.mockMvc.perform(get(USER_ENDPOINT)).andExpect(status().isOk()).andReturn();
    	
    
    	//Get the JSON response 
    	//String jsonResponseActual = mvcResult.getResponse().getContentAsString();
//    	
//    	//ASSERT 
    	//assertThat(jsonResponseActual).isEqualTo(jsonResponseExpected);
//    	
    	//System.out.println("testListUsers() - actual list(JSON format): \n" + jsonResponseActual); 
    	
    	
    	
    	/**
    	 * restOperations
                .exchange(new URI(url), HttpMethod.GET, null, new ParameterizedTypeReference<Response>() {
                }).getBody().getContent();
                
            >>>>
            TESTS
            @Test
    public void testGetObjectAList() {
        ObjectA myobjectA = new ObjectA();
        //define the entity you want the exchange to return
        ResponseEntity<List<ObjectA>> myEntity = new ResponseEntity<List<ObjectA>>(HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
            Matchers.eq("/objects/get-objectA"),
            Matchers.eq(HttpMethod.POST),
            Matchers.<HttpEntity<List<ObjectA>>>any(),
            Matchers.<ParameterizedTypeReference<List<ObjectA>>>any())
        ).thenReturn(myEntity);

        List<ObjectA> res = underTest.getListofObjectsA();
        Assert.assertEquals(myobjectA, res.get(0));
    }
    	 */
    	
    }

    /**
     * Response reeponse = restOperations
                .exchange(new URI(url), HttpMethod.GET, null, new ParameterizedTypeReference<Response>() {
                }).getBody().getContent();
     * @throws JsonProcessingException 
     */
    

//    @Test
//    public void whenSaveManyToManyRelationship_thenCorrect() {
//        Author author1 = new Author(AUTHOR_NAME);
//        template.postForEntity(AUTHOR_ENDPOINT, author1, Author.class);
//
//        Book book1 = new Book("Animal Farm");
//        template.postForEntity(BOOK_ENDPOINT, book1, Book.class);
//
//        Book book2 = new Book("1984");
//        template.postForEntity(BOOK_ENDPOINT, book2, Book.class);
//
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Content-type", "text/uri-list");
//        HttpEntity<String> httpEntity = new HttpEntity<>(
//          BOOK_ENDPOINT + "/1\n" + BOOK_ENDPOINT + "/2", requestHeaders);
//        template.exchange(AUTHOR_ENDPOINT + "/1/books", 
//          HttpMethod.PUT, httpEntity, String.class);
//
//        String jsonResponse = template
//          .getForObject(BOOK_ENDPOINT + "/1/authors", String.class);
//        JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
//        JSONArray jsonArray = jsonObj.getJSONArray("authors");
//        assertEquals("author is incorrect", 
//          jsonArray.getJSONObject(0).getString("name"), AUTHOR_NAME);
//    }
    
    
    @Test 
    public void testGetAllUsers()
    {
    	ResponseEntity<GetResponseUser> responseEntity = this.restTemplate.exchange(USER_ENDPOINT, HttpMethod.GET, 
    			null, new ParameterizedTypeReference<GetResponseUser>() {
		});
    	
    	
    	//Get the Response 
    	GetResponseUser response = responseEntity.getBody();
    	
    	
    	this.logger.info("in gtestGetAllUsers(): users" + response);
    	
    }

    
    @Test
    public void testGetResponseUser() throws JsonProcessingException
    {
    	//ARRANGE 
    	//MOCKING data 
    	List<User> users = new ArrayList<User>(); 
    	User user1 = new User(1, "nir@gmail.com", "", "Nir", "Itzhak","", false);
    	User user2 = new User(2, "niron@gmail.com", "", "Niron", "Itzhak","", true);
    	User user3 = new User(3, "shalom@gmail.com", "", "Shalom", "Itzhak","", true);
    	
    	users.add(user1);
    	users.add(user2);
    	users.add(user3);
    	

    	//>>>>>>>>>>>>>>>>
    	//Define the entity I want the exchange to return 
    	GetResponseUser getResponseUser = new GetResponseUser();
    	
    	UserEmbedded embedded = new UserEmbedded();
    	
    	embedded.setUsers(users);
    	
    	getResponseUser.setEmbedded(embedded);
    	
    	//System.out.println(getResponseUser); 
    	
    	String jsonReponse = this.mapper.writeValueAsString(getResponseUser);
    	
    	System.out.println(jsonReponse); 
    	
    }
}
