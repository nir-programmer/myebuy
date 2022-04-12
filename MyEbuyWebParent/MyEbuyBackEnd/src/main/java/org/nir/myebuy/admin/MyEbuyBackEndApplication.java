package org.nir.myebuy.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"org.nir.myebuy.common.entity", "org.nir.myebuy.admin.user"})
public class MyEbuyBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEbuyBackEndApplication.class, args);
	}

}
