package com.dvosoftwarehouse.blackjack21.api;

import java.lang.management.ManagementFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
@SpringBootApplication
@EntityScan("com.dvosoftwarehouse.blackjack21.api.entities")
public class Blackjack21Api {

	public static void main(String[] args) {
		System.out.printf("Hibernate Version %s\n", org.hibernate.Version.getVersionString());
		log.info("[PROCESSOR] count: {}", Runtime.getRuntime().availableProcessors());
		log.info("[THREAD] Current threads on main group: {}", Thread.activeCount());
		log.info(
				"[THREAD] Current threads on all groups: {}",
				ManagementFactory.getThreadMXBean().getThreadCount());

		SpringApplication.run(Blackjack21Api.class, args);
	}

}
