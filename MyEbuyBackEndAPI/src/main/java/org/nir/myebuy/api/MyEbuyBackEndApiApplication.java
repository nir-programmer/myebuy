package org.nir.myebuy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"org.nir.myebuy.api.dao", "org.nir.myebuy.common.entity"})
public class MyEbuyBackEndApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEbuyBackEndApiApplication.class, args);
	}

}
