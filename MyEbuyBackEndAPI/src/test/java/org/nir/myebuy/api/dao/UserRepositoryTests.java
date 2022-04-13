package org.nir.myebuy.api.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
//For testing with the real DB (instead H2 in memory used by Spring) 
@AutoConfigureTestDatabase(replace = Replace.NONE)
//To update the real db
@Rollback(false)
public class UserRepositoryTests 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager; 
	
	@Test
	public void testSmoke()
	{
		assertThat(this.userRepository).isNotNull();
		assertThat(this.entityManager).isNotNull();
	}
	
	@Test
	@DisplayName("Should return correct list of users from db")
	public void testListAllUsers()
	{
		//GIVEN
		int expectedUsers = 26; 
		
		//WHEN
		List<User> users = this.userRepository.findAll();
		int actualUsers = users.size();
		
		//THEN
		assertThat(expectedUsers).isEqualTo(expectedUsers);
		
		
		users.stream().forEach(System.out::println);
	
	}

}
