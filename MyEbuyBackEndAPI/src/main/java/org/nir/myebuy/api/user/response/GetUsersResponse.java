package org.nir.myebuy.api.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUsersResponse {
	
	    @JsonProperty("_embedded")
	    private UserEmbedded embedded;

	    public UserEmbedded getEmbedded() {
	        return embedded;
	    }

	    public void setEmbedded(UserEmbedded embedded) {
	        this.embedded = embedded;
	    }
	
	
}
