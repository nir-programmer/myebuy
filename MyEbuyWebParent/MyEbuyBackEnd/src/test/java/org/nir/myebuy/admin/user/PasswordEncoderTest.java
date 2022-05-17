package org.nir.myebuy.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderTest 
{
	@Test
	public void testEncodePassword()
	{
		//GIVEN
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "nam2020"; 
		
		//WHEN
		String encodedPassword = passwordEncoder.encode(rawPassword);
		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
	
		//THEN
		System.out.println(encodedPassword); 
		assertThat(matches).isTrue();
		
	}

}
