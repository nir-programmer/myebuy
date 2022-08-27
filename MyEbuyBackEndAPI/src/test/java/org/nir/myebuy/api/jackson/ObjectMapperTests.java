package org.nir.myebuy.api.jackson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.api.user.RestUsersTests;
import org.nir.myebuy.api.user.response.GetUsersResponse;
import org.nir.myebuy.api.user.response.UserEmbedded;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
@Disabled
public class ObjectMapperTests {
	

	private static final Logger logger = Logger.getLogger(RestUsersTests.class);
	
	@Autowired
	private static ObjectMapper mapper;
	
	@BeforeAll
	public static void init()
	{
		mapper = new ObjectMapper();
	}
	
	@Test
	public void testSmoke() {
		assertThat(mapper).isNotNull();
		logger.log(Level.INFO, "mapper : " + mapper);
	}
	
	@Test
	public void whenParsingJsonStringIntoJsonNode_thenCorrect() 
	  throws JsonParseException, IOException {
	    String jsonString = "{\"k1\":\"v1\"}";

	    //ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(jsonString);

	    assertNotNull(actualObj);
	   logger.log(Level.INFO, "actualString: " + actualObj);
	}
	
	
	@Test
	public void testGetAllUserEmbedded() throws JsonProcessingException
	{
		List<User> users = new ArrayList<>(); 
		
    	User user1 = new User(1, "nir@gmail.com", "", "Nir", "Itzhak","", false);
    	User user2 = new User(2, "niron@gmail.com", "", "Niron", "Itzhak","", true);
    	User user3 = new User(3, "shalom@gmail.com", "", "Shalom", "Itzhak","", true);
    	
    	users.add(user1);
    	users.add(user2);
    	users.add(user3);
		
		UserEmbedded userEmbedded = new UserEmbedded();
		
		userEmbedded.setUsers(users);
		
		
		//System.out.println("userEmbedded:\n" + userEmbedded ); 
		
		
		String json = this.mapper.writeValueAsString(userEmbedded);
		
		System.out.println("json:\n" + json);
		
	
	}
	
	@Test
	public void testGetUsersResponseGetUsers() throws JsonProcessingException
	{
		
		GetUsersResponse response = new GetUsersResponse();
		
		List<User> users = response.getEmbedded().getUsers();
		
		String usersJson = this.mapper.writeValueAsString(users); 
		
		System.out.println(">>testGetUsersResponseGetUsers(): usersJson:\n" + usersJson);
	}

	
//	@Test
//	public void 
//	givenUsingLowLevelApi_whenParsingJsonStringIntoJsonNode_thenCorrect() throws IOException {
//		String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
//		
//		//ObjectMapper mapper = new ObjectMapper();
//		  //JsonNode actualObj = mapper.readTree(jsonString);
//		  
//		  JsonFactory factory = mapper.getFactory();
//		  JsonParser parser = factory.createParser(jsonString);
//		  JsonNode actualObj = mapper.readTree(parser);
//		  
//		  assertNotNull(actualObj);
//		  
//		  this.logger.log(Level.INFO, "actualObject after readTree(): " + actualObj);
//
//		 
//	}
//	
//	@Test
//	public void givenTheJsonNode_whenRetrievingDataFromId_thenCorrect() throws JsonMappingException, JsonProcessingException
//	{
//		String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
//		JsonNode actualObj = mapper.readTree(jsonString);
//		
//		
//		//WHEN
//		JsonNode jsonNode1 = actualObj.get("k1"); 
//		
//		
//		//THEN
//		assertThat(jsonNode1.textValue()).isEqualTo("v1");
//	}
	
//	@Test
//	public void testGetExapleStructure() throws IOException
//	{
//		JsonNode rootNode = ExampleStructure.getExampleRoot(); 
//		
//		assertThat(rootNode).isNotNull();
//		
//		
//		
//	}
//

}


