package org.nir.myebuy.api.role.response;

import org.nir.myebuy.api.user.response.UserEmbedded;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetResponseRoles {
	
	  @JsonProperty("_embedded")
	    private RoleEmbedded embedded;

		public RoleEmbedded getEmbedded() {
			return embedded;
		}
	
		public void setEmbedded(RoleEmbedded embedded) {
			this.embedded = embedded;
		}
	
	    
	
	

}
