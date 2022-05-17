package org.nir.myebuy.api.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.nir.myebuy.api.role.RoleRepository;
import org.nir.myebuy.api.user.UserRepository;
import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test-h2-persist")
public class LoadH2DataBase {
	private static final Logger log = LoggerFactory.getLogger(LoadH2DataBase.class);
	
	//	public User(String email, String password, String firstName, String lastName, String photos, boolean enabled) {

	@Bean
	CommandLineRunner initH2DataBase(UserRepository userRepository, RoleRepository roleRepository)
	{
		return args -> {
			
//			//ARRANGE
////			User ravi = new User("ravi@gmail.com", "ravi2022", "Ravi", "Kumar"); 
////			Role roleEditor = new Role(3); 
////			Role roleAssistance = new Role(5); 
////			
////			//ASSERT
////			ravi.addRole(roleAssistance);
////			ravi.addRole(roleEditor);
////			
////			User savedUser = userRepository.save(ravi); 
//		
//			
////			Role role1 = new Role("Admin", "Manage Everything");
////			
////			Role role2 = new Role("Shipper", "Manage Orders");
////			
////			Set<Role> roles = new HashSet<>();
////			roles.add(role1);
////			roles.add(role2);
////			
////			
////			User user1 = new User("niritzhak10@gmail.com", "superduper100", "Nir", "Itzhak", "nir-10", true);
////			
//			//User user2 = new User("niron10@gmail.com", "superduper200", "Niron", "Itzhak", "nir-10", false);
//			
//			//user1.setRoles(roles);
//			
////			user1.addRole(role1);
////			user1.addRole(role2);
////			user1.setRoles(roles);
////			
////			user1.getRoles().forEach(System.out::println);
////			
////			//user2.addRole(role2);  
////			
////			
////			log.info("Preloading " + 
////					roleRepository.save(role1));
////			
////			log.info("Preloading " + 
////					roleRepository.save(role2));
////			
////			
////			
////			log.info("Preloading " + 
////					userRepository.save(user1));
////			
////			log.info("Preloading " + 
////					userRepository.save(user2));
//			
			
			
			
		};
	}

}
