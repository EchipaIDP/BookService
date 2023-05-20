package com.spring.catalogapp;

import com.spring.catalogapp.entity.Product;
import com.spring.catalogapp.repository.ProductRepository;
import com.spring.catalogapp.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CatalogappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogappApplication.class, args);
	}
}
