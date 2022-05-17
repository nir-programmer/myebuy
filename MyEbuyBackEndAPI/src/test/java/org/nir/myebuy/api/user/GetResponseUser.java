package org.nir.myebuy.api.user;

import org.nir.myebuy.api.user.response.UserEmbedded;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetResponseUser{
    @JsonProperty("_embedded")
    private UserEmbedded embedded;

    public UserEmbedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(UserEmbedded embedded) {
        this.embedded = embedded;
    }
}


//class UserEmbedded{
//    @JsonProperty("users")
//    List<User> users = new ArrayList<User>();
//
//    public UserEmbedded(){
//
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//}


//class Order {
//    private int id;
//    private String name;
//    private String keyword;
//    private Agent agent;
//
//    @Override
//    public String toString() {
//        return "Order [id=" + id + ", name=" + name + ", keyword=" + keyword + "]";
//    }
//
//    public Order(){
//
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//
//
//    public Agent getAgent() {
//        return agent;
//    }
//
//    public void setAgent(Agent agent) {
//  
//    }}