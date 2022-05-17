package org.nir.myebuy.api.user;

import org.nir.myebuy.common.entity.User;

/**
 * expected: "[{"id":1,"email":"nir@gmail.com","password":"","firstName":"Nir","lastName":"Itzhak","photos":"","enabled":false,"roles":[]},
			{"id":2,"email":"niron@gmail.com","password":"","firstName":"Niron","lastName":"Itzhak","photos":"","enabled":true,"roles":[]},{"id":3,"email":"shalom@gmail.com","password":"","firstName":"Shalom","lastName":"Itzhak","photos":"","enabled":true,"roles":[]}]"
	
	 but was: "{"_links":{"self":{"href":"http://localhost/users"}}}"

 * 
 * CURL: 
 * {
  "_embedded" : {
    "users" : [ {
      "id" : 1,
      "email" : "postman@gmail.com",
      "password" : "$2a$10$ByVsBni9ndOJsP/Isjo.7Oysv7bzqX.QZSA/xCWaRexEeZLzWiT/a",
      "firstName" : "NIRON",
      "lastName" : "Mannnn",
      "photos" : "",
      "enabled" : false,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8083/MyEbuyAdminAPI/api/employees/1"
        },
        "users" : {
          "href" : "http://localhost:8083/MyEbuyAdminAPI/api/users"
        }
      }
    },
 * @author nir
 *
 */
public class GetResponseUsers {
	//private Object _embedded = 

}


