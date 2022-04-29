package org.nir.myebuy.api.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.api.user.RoleRepository;
import org.nir.myebuy.common.entity.Role;
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
public class RoleRepositioryTests 
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private RoleRepository repository; 
	
	@Test
	public void testSmoke()
	{
		assertThat(entityManager).isNotNull();
		assertThat(this.repository).isNotNull();
		

	}
	
//	
//	
	@Test
	public void testGetAllRoles()
	{
		int expectedNumberOfRoles = 5; 
		List<Role> roles ; 
		
		//ACT
		roles = this.repository.findAll();
		int actualNumberOfRoles = roles.size();
		
		//ASSERT
		assertThat(roles).isNotNull();
		assertThat(expectedNumberOfRoles).isEqualTo(actualNumberOfRoles);
		
		roles.stream()
		.map(role -> role.getId())
		.forEach(System.out::println);
	}
	
	@Test
	@Disabled
	public void testCreateRole()
	{
		//GIVEN 
		Role role = new Role("TestRole", "Test Role to be deleted!!");
		
		//ACT 
		Role savedRole = this.repository.save(role); 
		
		//ASSERT
		assertThat(savedRole).isNotNull();
		
		System.out.println(savedRole + " with id " + savedRole.getId()); 
		
		
	}
	
	
	
	
	

}
