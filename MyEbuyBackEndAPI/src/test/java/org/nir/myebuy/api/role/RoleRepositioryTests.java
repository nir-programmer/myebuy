package org.nir.myebuy.api.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.api.role.RoleRepository;
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
//@Disabled
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
	
	//NOTE: THE ID IS NOT SET TO 0 AUTOMATICALLY AFTER REMOVING - FOR NOW I KNOW HOW RESET TO 0 ONLY FROM THE DB USING TRUNCATE
	@Test
	//@Disabled
	public void testCreateFirstRole()
	{
		//GIVEN 
		Role role = new Role("Admin", "manage everything");
		
		//ACT 
		Role savedRole = this.repository.save(role); 
		
		//ASSERT
		assertThat(savedRole).isNotNull();
		
		System.out.println(savedRole + " with id " + savedRole.getId()); 
		
		
	}
	
	//OK
	@Test
	@Disabled
	public void testCreateRestRoles()
	{
		//ARRANGE
		Role roleSalesperson = new Role("Salesperson", "manage product price, customers, shippin, orders and sales reports");
		Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
		Role roleShipper = new Role("Shipper", "view products , view orders and update order status"); 
		Role roleAsistance = new Role("Assistance", "manage questions and reviews"); 
		
		
		//ACT
		//Save with batch of JPA 
		List<Role> savedRoles = (List<Role>) this.repository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper ,roleAsistance)); 
		
		//ASSERT
		assertThat(savedRoles).isNotNull();
		assertThat(savedRoles.size()).isEqualTo(4);
		
		
		System.out.println("testCreateRestRoles(): rest of the persisted roles: " ); 
		savedRoles.stream().forEach((System.out::println)); 
	}
	

	
	//@Disabled
	@Test
	public void testGetAllRoles()
	{
		int expectedNumberOfRoles = 5; 
		List<Role> roles ; 
		
		//ACT
		roles = (List<Role>) this.repository.findAll();
		int actualNumberOfRoles = roles.size();
		
		//ASSERT
		assertThat(roles).isNotNull();
		assertThat(expectedNumberOfRoles).isEqualTo(actualNumberOfRoles);
		
		roles.stream()
		.map(role -> {
			System.out.println("Role Name: " + role.getName());
			return role.getId();
		})
		.forEach(System.out::println);
	}
	
	//BEFORE UPDATING THE ROLE Descriptoin :role description: Manage evertything - OK 
	@Test
	//@Disabled
	public void testGetRoleById()
	{
		Integer id = 1; 
		Role role = this.repository.findById(id).get(); 
		
		assertThat(role).isNotNull();
		assertThat(role.getId()).isEqualTo(id); 
		
		System.out.println(">>testGetRoleById(): Role with id: " + id);
		System.out.println("\trole name:" + role.getName());
		System.out.println("\trole description: " + role.getDescription() );
		
	}
	
	
	@Test
	@Disabled
	public void testUpdateRole()
	{
		Integer id = 1; 
		String description = "manage everything";
		Role role = this.entityManager.find(Role.class, id);
		
		role.setDescription(description);
		
		Role updatedRole = this.entityManager.persist(role);
		
		
		assertThat(updatedRole).isNotNull();
		assertThat(updatedRole.getId()).isEqualTo(1);
		assertThat(updatedRole.getDescription()).isEqualTo(description);
		

	}
	
	@Test
	@Disabled
	public void testDeleteRole()
	{
		Integer roleId = 1; 
		
		this.repository.deleteById(roleId);
		
		Optional<Role> roleOptional = this.repository.findById(roleId);
		
		assertThat(roleOptional.isPresent()).isFalse();  
		
		System.out.println(">>testDeleteRole(): roleOptional = " + roleOptional);
		
	}
	
	
	//OK
	@Test
	public void testCountRoles()
	{
		//GIVEN
		long expectedCount = 5; 
		
		
		//WHEN
		long actualCount =this.repository.count(); 
		
		
		
		//THEN
		assertThat(expectedCount).isEqualTo(actualCount);
		
		
	}
	
	
	/**OK
	 * NOTE: Can not delete a role if there are users refer to it !! try first delete all users
	 * BUT IT IS POSSIBLE TO DELETE ALL USERS !! OK
	 * 
	 * After remove all users - try to remove all roles - WORKS!!!!!!!!!!!!!!!!!!!!
	 */
	@Test 
	public void testDeleteAllRoles()
	{
		//GIVEN
		Integer expectedNumberOfRolesAfterDeleteAll = 0; 
		
		
		//WHEN
		this.repository.deleteAll(); 
		List<Role> roles = (List<Role>) this.repository.findAll();
		Integer actualNumberOfRolesAfterDeleteAll = roles.size();
		//THEN
		assertThat(actualNumberOfRolesAfterDeleteAll).isEqualTo(expectedNumberOfRolesAfterDeleteAll) ;
		
		System.out.println(">>testDeleteAllRoles() - number of roles after delete all: " + actualNumberOfRolesAfterDeleteAll); 
		
	}
	
	
	
	
	
	
	

}
