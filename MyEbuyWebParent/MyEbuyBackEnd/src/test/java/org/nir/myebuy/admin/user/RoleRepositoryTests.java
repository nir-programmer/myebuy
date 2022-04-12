package org.nir.myebuy.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
//For testing with the real DB (instead H2 in memory used by Spring) 
@AutoConfigureTestDatabase(replace = Replace.NONE)
//To update the real db
@Rollback(false)

public class RoleRepositoryTests 
{
//	@Autowired
//	private RoleRepository roleRepository; 
//	
//	//OK - The roles table created by Hibernate!
//	@Test
//	public void smokeTest()
//	{
//		assertThat(this.roleRepository).isNotNull(); 
//	}
//	
//	@Test
//	public void testCreateFirstRole()
//	{
//		//GIVEN
//		Role role = new Role("Admin", "manage everything"); 
//		
//		//WHEN
//		Role savedRole = this.roleRepository.save(role); 
//		
//		
//		//THEN
//		assertThat(savedRole).isNotNull(); 
//		assertThat(savedRole.getId()).isEqualTo(1); 
//		
//		
//		System.out.println("testCreateRole(): saved role: " + savedRole); 
//	}
//	
//	
//	//OK
//	@Test
//	public void testCreateRestRoles()
//	{
//		//ARRANGE
//		Role roleSalesperson = new Role("Salesperson", "manage product price, customers, shippin, orders and sales reports");
//		Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
//		Role roleShipper = new Role("Shipper", "view products , view orders and update order status"); 
//		Role roleAsistance = new Role("Assistance", "manage questions and reviews"); 
//		
//		
//		//ACT
//		//Save with batch of JPA 
//		List<Role> savedRoles = (List<Role>) this.roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper ,roleAsistance)); 
//		
//		//ASSERT
//		assertThat(savedRoles).isNotNull();
//		assertThat(savedRoles.size()).isEqualTo(4);
//		
//		
//		System.out.println("testCreateRestRoles(): rest of the persisted roles: " ); 
//		savedRoles.stream().forEach((System.out::println)); 
//	}
//	
//	
//	/**
//	 * Exception exception = assertThrows(NumberFormatException.class, () -> {
//        Integer.parseInt("1a");
//    });
//
//	 */
////	@Test
////	public void testCreateRoleWithNoNameSouldThrow()
////	{
////		//GIVEN
////		Role role = new Role("Role without name!"); 
////		//WHEN
////		
////		this.roleRepository.save(role); 
////		
////		//THEN AND WHEN
//////		Exception exception = assertThrows(Exception.class, () -> this.roleRepository.save(role));
//////		
//////		assertThat(exception).isNotNull();
////		}
////	

}
