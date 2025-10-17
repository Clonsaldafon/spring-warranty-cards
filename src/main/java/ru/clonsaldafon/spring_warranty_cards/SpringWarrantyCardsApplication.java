package ru.clonsaldafon.spring_warranty_cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringWarrantyCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWarrantyCardsApplication.class, args);
	}

}
