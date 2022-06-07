package org.nir.myebuy.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Bean
	public PasswordEncoder PasswordEncoder()
	{
		return new  BCryptPasswordEncoder(); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
//		http.authorizeRequests().anyRequest().permitAll()
		http.authorizeHttpRequests().antMatchers("/").permitAll()
		.antMatchers("/h2-console/**").permitAll();
		
        http.csrf().disable();
        http.headers().frameOptions().disable();

	}
//	//I added this - should I 
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**");
//	}

	
	/**
	 * @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	 */
	
	/**
	 * @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
	 */
	

}
