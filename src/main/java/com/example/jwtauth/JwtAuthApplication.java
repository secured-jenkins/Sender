package com.example.jwtauth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.persistence.CarRepo;
import com.example.persistence.entities.Car;

@ComponentScan({ "com.example.controllers", "com.example.jwt", "com.example.services", "com.example.security", "com.example.configurations"})
@EntityScan({ "com.example.persistence.entities" })
@EnableJpaRepositories("com.example.persistence")
@EnableDiscoveryClient
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class JwtAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

//	@Bean
//	CommandLineRunner cLR(CarRepo carR) {
//		return args -> {
//			Set<Car> list1 = new HashSet<Car>();
//			list1.add(new Car("BMW"));
//			list1.add(new Car("MERCEDES"));
//			carR.save(new Car("BMW"));
//			carR.save(new Car("MERCEDES"));
//			carR.save(new Car("AUDI"));
//			carR.save(new Car("PORSCHE"));
//			carR.save(new Car("RAM"));
//			carR.save(new Car("DODGE"));
//			carR.save(new Car("SUBARU"));
//			carR.save(new Car("JAGUAR"));
//			carR.save(new Car("MASERATI"));
//		};
//	}

}
