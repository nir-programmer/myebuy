package org.nir.myebuy.api.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

//import org.jboss.logging.Logger;
//import org.junit.jupiter.api.Test;
//import org.nir.ecommerce.entity.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


/**
 *  start the application and listen for a connection (as it would do in production)
 *  and then send an HTTP request and assert the response
 *  The server will be started with the entire Spring Context
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestUsersTests 
{
	private static final Logger logger = Logger.getLogger(RestUsersTests.class);

	
    @LocalServerPort
    private int port ;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void smoke()
    {
    	
    	assertThat(this.testRestTemplate).isNotNull();
    	logger.info("The TestRestTemlate Injected Succesffully");
    }

    @Test
    public void testListUsersResponseAsString()
    {
        String url = "http://localhost:" + port + "/MyEbuyAdminAPI/api/users";

        String strResponse = this.testRestTemplate.getForObject(url,  String.class);
        assertThat(strResponse).contains("Ha Minh");

        System.out.println(">>testResponseAsString(): The response:\n" + strResponse);
//


    }

    @Test
    public void testListProductCategoryResponseAsString()
    {
        String url = "http://localhost:" + port + "/api/product-category";

        String strResponse = this.testRestTemplate.getForObject(url,  String.class);
        assertThat(strResponse).contains("Books");

        System.out.println(">>testResponseAsString(): The response:\n" + strResponse);
//

    }

    /**
     *  Notes:
     *          * 1.Spring Data JPA will perform the SELECT * FROM product WHERE category_id = ?
     *          * 2.Spring Data REST exposes the endpoint: http://localhost:8080/api/products/search/findByCategoryId?id=2
     *      *
     */
    @Test
    public void testGetProductByCategoryIdResponseAsString()
    {
        //http://localhost:8080/api/products/search/findByCategoryId?id=2
        int id = 2;
        String url = "http://localhost:" + port + "/api/products/search/findByCategoryId?id=" + id;

        String strResponse = this.testRestTemplate.getForObject(url,  String.class);
       assertThat(strResponse).contains("COFFEEMUG-1001");

        System.out.println(">>testResponseAsString(): The response:\n" + strResponse);
//


    }

    /**
     * String url = "http://localhost:" + port + "/api/customers";
     * 		int expectedCount = 7;
     *
     *
     * 		//WHEN
     * 		//make REST call
     * 		ResponseEntity<List<Customer>> responseEntity = this.restTemplate.exchange(url,
     * 					HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {});
     *
     *
     * 		//THEN
     * 		//Get the list of customers from the Response(Observable)
     * 		List<Customer> actualCustomers = responseEntity.getBody();
     *
     * 		assertThat(actualCustomers).isNotNull();
     * 		assertThat(actualCustomers.size()).isEqualTo(expectedCount);
     *
     *
     *
     * 		logger.info("in testListAll(): success! customers \n " + actualCustomers);
     */
    @Test
    public void testGetAllProducts()
    {
    	//GIVEN
    	List<User> users = null;
    	String url = "http://localhost:" + port + "/api/users";
        int expectedCount = 5; 
        
    	
    	//WHEN
        //make REST call
        //ResponseEntity<List<Product>> responseEntity =
//        this.testRestTemplate.exchange(url, HttpMethod.GET,
//                null, new ParameterizedTypeReference<List<Product>>() {});
    	//this.testRestTemplate.getForObject(url, null)

        //List<Product> actualProducts = responseEntity.getBody();


        //THEN
        //assertThat(actualProducts).isNotNull();
       // assertThat(actualProducts.size()).isEqualTo(expectedCount);

        //logger.info("in testListAll(): success! customers \n " + actualProducts);
    }



}
