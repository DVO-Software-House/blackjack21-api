package com.dvosoftwarehouse.blackjack21.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.dvosoftwarehouse.blackjack21.api.entities")
public class Blackjack21Api {

	public static void main(String[] args) {
		System.out.printf("Hibernate Version %s\n", org.hibernate.Version.getVersionString());
		SpringApplication.run(Blackjack21Api.class, args);
	}

}
