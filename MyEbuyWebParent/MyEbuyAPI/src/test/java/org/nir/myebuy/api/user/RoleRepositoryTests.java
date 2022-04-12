package org.nir.myebuy.api.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests 
{
	
	@Autowired
	private RoleRepository roleRepository; 

	
	private ObjectMapper mapper ; 
	@BeforeEach
	public void setup()
	{
		this.mapper = new ObjectMapper(); 
		
	}
	
	@Test
	public void testSmoke()
	{
		assertThat(this.roleRepository).isNotNull();
		assertThat(this.mapper).isNotNull();
	}
	
	
	/**
	 * ObjectMapper objectMapper = new ObjectMapper();
		Car car = new Car("yellow", "renault");
		objectMapper.writeValue(new File("target/car.json"), car);
	 * @throws IOException 
	 * @throws DatabindException 
	 * @throws StreamWriteException 
	 */
	/**
	 *  // Getting the file by creating object of File class
        File f = new File("F:\\program.txt");
 
        // Checking if the specified file exists or not
        if (f.exists())
 
            // Show if the file exists
            System.out.println("Exists");
        else
 
            // Show if the file does not exists
            System.out.println("Does not Exists");
	 * @throws StreamWriteException
	 * @throws DatabindException
	 * @throws IOException
	 */
	@Test
	public void testConvertListOfRolesFromDBToJsonFile() throws StreamWriteException, DatabindException, IOException
	{
		String fileUrl = "data/users/users.json"; 
		
		File file = new File(fileUrl); 
	
		List<Role> roles = (List<Role>) this.roleRepository.findAll();
		
		mapper.writeValue(file, roles);
		
		
		 
		
//		assertThat(roles).isNotNull();
//		assertThat(roles.size()).isEqualTo(5); 
//		
//		
//		roles.stream().forEach((System.out::println)); 
		
		//this.mapper.co
	}
	
	}
