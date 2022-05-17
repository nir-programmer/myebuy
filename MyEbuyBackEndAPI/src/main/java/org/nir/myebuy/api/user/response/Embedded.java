package org.nir.myebuy.api.user.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Embedded<T> 
{
	//protected List<T> items;
	
	@JsonProperty("users")
   protected  List<T> items = new ArrayList<T>();

    public Embedded(){}
    
    public List<T> getAll() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
