package org.nir.myebuy.common.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="customUser", 
types = {User.class})
public interface CustomUser {
	@Value("#{target.id}")
	Integer getId();
	
	String getEmail(); 
	
	String getFirstName();
	
	String getLastName();
	
	String getPhotos();
	
  boolean isEnabled(); 
	
	List<Role> getRoles();
	
	/**
	 * @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ; 
	
	@Column(length = 128, nullable = false, unique = true)
	private String email ;
	
	//64 characters for using password encoder and the length of the encoded password will be 64 characters
	@Column(length = 64, nullable = false)
	private String password; 
	
	@Column(name  = "first_name", length = 45, nullable = false)
	private String firstName; 
	
	@Column(name  = "last_name", length = 45, nullable = false)
	private String lastName; 
	
	@Column(length = 64)
	private String photos; 
	
	//This field can be null and will be mapped with the same name of the property 
	private boolean enabled; 

	 */

}
