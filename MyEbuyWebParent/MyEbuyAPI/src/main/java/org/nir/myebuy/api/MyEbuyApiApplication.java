package org.nir.myebuy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"org.nir.myebuy.api.user", "org.nir.myebuy.common.entity"})
@SpringBootApplication
public class MyEbuyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEbuyApiApplication.class, args);
	}

}
