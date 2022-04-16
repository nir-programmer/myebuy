package org.nir.myebuy.common.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	
	@Id
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
	
//	@Transient
//	private Integer ratings = 3;

	
	/** IMPORTANT
	 * 
	 * This class: User
		 * 1. @JoinTable tells Hibernate: 
		 * 
			 * 	-Look at the user_id column in the users_roles table 
			 * 
			 *  - For other side (inverse) , look at the role_id column in the users_roles 
			 *  
			 *  - Hibernate uses this info to find the relationship between users and roles 
		 *  
		 *  
		 * 2.  The inverse:
		 *  
		 *   	- I'm defining the relationship in the User class
		 *   
		 *   	- The Role class is on the other side -> so it is considred as the "inverse"! 
		 *   		
		 *   
		 *   Requirement: If delteing a User - dont delete Role! - OK - dont cascading on delete 
	 *  
	 *  	TO-DO:
	 *  
	 *  	1. Define the fetching: lazy
	 *  
	 *  	2. Define the Cascade : all exepct DELETE 
	 *  
	 		 3. Define the @JoinTable
	 *  
	 *  	@ManyToMany(fetch = FetchType.LAZY, 
	 *  			cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
	 *  			cascaseType.DETACH, CascadeType.REFREASH}
	 *  	@JoinTable(name = "users_roles", 
	 *  		joinColumns = @JoinColumn(name = "user_id"), 
	 *  		inveersJoinColumns = @JoinColumn(name="role_id"
	 *  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	 *  
	 * NOTE: The many to many relationshiop is UNI DIRECTIONAL - from user to roles 
	 */
	@ManyToMany
	//Intermidate table with the F.Ks
	@JoinTable(
				name = "users_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	//This is the ivnerse! -other side 
	private Set<Role> roles = new HashSet<>();

	
	
	
		
	public User() {}
	
	
	
//	public User(String email, String password, String firstName, String lastName, Set<Role> roles) {
//		this(email, password, firstName, lastName); 
//		this.roles = roles;
//	}



	public User(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photoes) {
		this.photos = photoes;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	} 
	
	
	public void addRole(Role role) 
	{
		this.roles.add(role); 
	}

	

	//DEMO
	

	


	
//	public Integer getRatings() {
//		return ratings;
//	}



	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", photos=" + photos + ", enabled=" + enabled + ", roles=" + roles + "]";
	}


//
//	public void setRatings(Integer ratings) {
//		this.ratings = ratings;
//	}



	


		
	
	
	
	
	

}
