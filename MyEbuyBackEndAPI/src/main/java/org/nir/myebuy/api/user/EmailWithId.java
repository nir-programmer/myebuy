package org.nir.myebuy.api.user;

class EmailWithId{
	private Integer id;
	private String email;
	
	
	public EmailWithId() {}
	
	
	public EmailWithId(Integer id, String email) {
		super();
		this.id = id;
		this.email = email;
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
	
	
}
