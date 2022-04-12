package org.nir.myebuy.api.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRoleRepositoryTests
{
	
	@Autowired
	private TestEntityManager entityManager; 
	
	@Autowired
	private UserRepository userRepository; 
	
	
	private ObjectMapper mapper ; 
	
	@BeforeEach
	public void setup()
	{
		this.mapper = new ObjectMapper(); 
	}
	
	
	@Test
	public void testSmoke()
	{
		assertThat(this.entityManager).isNotNull();
		assertThat(this.userRepository).isNotNull();
		assertThat(this.mapper).isNotNull();
	}
	
	@Test
	public void testReadUsersFromDbIntoJsonFile() throws StreamWriteException, DatabindException, IOException
	{

		String fileUrl = "data/users/users.json"; 
		
		File file = new File(fileUrl); 
		
		List<User> users = (List<User>) this.userRepository.findAll();
		
		this.mapper.writeValue(file , users);
		
		//assertThat(file.exists()).isTrue();
		
	}
	
	/*
	 * expected: "{"id":1,"email":"niritzhak10@gmail.com","password":"superduper100","firstName":"Nir","lastName":"Itzhak","photoes":null,"enabled":false,"roles":[{"id":1,"name":"Admin","description":"manage everything"}]}"
	 */
//	@Test
//	public void testGetUserById() throws JsonProcessingException
//	{
//		//ARRANGE
//		Integer id = 1; 
//		String expectedUserJson ="{\n"
//				+ "        \"email\": \"niritzhak10@gmail.com\",\n"
//				+ "        \"enabled\": false,\n"
//				+ "        \"firstName\": \"Nir\",\n"
//				+ "        \"id\": 1,\n"
//				+ "        \"lastName\": \"Itzhak\",\n"
//				+ "        \"password\": \"superduper100\",\n"
//				+ "        \"photoes\": null,\n"
//				+ "        \"roles\": [\n"
//				+ "            {\n"
//				+ "                \"description\": \"manage everything\",\n"
//				+ "                \"id\": 1,\n"
//				+ "                \"name\": \"Admin\"\n"
//				+ "            }\n"
//				+ "        ]\n"
//				+ "    }"; 
//		
//		//ACT
//		User user = this.userRepository.findById(id).get();
//		
//		String actualUserJson = mapper.writeValueAsString(user);
//		//String userJson = mapper.convertValue(user, String.class);
//		
//		assertThat(expectedUserJson).isEqualTo(actualUserJson);
//		
//		//ASSERT
////		assertThat(user).isNotNull();
////		assertThat(user.getId()).isEqualTo(1); 
////		
////		System.out.println(">>testGetUserById(): The user with id " + id ); 
////		System.out.println(user); 
//	}
	
//	@Test 
//	public void testCreateUserWithRatings()
//	{
//		User user = new User("xxxx@gmail.com", "ssss", "XXXXXX", "UserRating");
//		
//		user.setRatings(3);
//		
//		User savedUser = this.userRepository.save(user); 
//		
//		assertThat(savedUser).isNotNull();
//		assertThat(savedUser.getRatings()).isEqualTo(3);
//		
//		System.out.println(savedUser.getRatings());
//		
//	}
	

}
