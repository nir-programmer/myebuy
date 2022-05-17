package org.nir.myebuy.api.user.response;

import java.util.ArrayList;
import java.util.List;

import org.nir.myebuy.common.entity.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEmbedded
{
	@JsonProperty("users")
	   protected  List<User> users = new ArrayList<User>();

	    public UserEmbedded(){}
	    
	   
		public List<User> getUsers() {
			return users;
		}


		public void setUsers(List<User> users) {
			this.users = users;
		}


		@Override
		public String toString() {
			return "UserEmbedded [items=" + users + "]";
		}

	    
	    

	
	

}
