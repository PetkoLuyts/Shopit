package com.example.shopitbackend.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.example.common.entity", "com.example.shopitbackend.admin.user"})
public class ShopitBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopitBackEndApplication.class, args);
	}

}
