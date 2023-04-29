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

	@Bean
	CommandLineRunner run(ProductService productService, ProductRepository productRepository) {
		return args -> {
			/***
			 * Initializing MongoDB database connectivity
			 */
			System.out.println("before connection");
            /*ConnectionString connectionString = new ConnectionString("mongodb+srv://user:user@hpetestcluster.ddqhjmn.mongodb.net/test?retryWrites=true&w=majority");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .serverApi(ServerApi.builder()
                            .version(ServerApiVersion.V1)
                            .build())
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("sample_airbnb");*/

			System.out.println("after connection");
			/***
			 * Initial Adding
			 */
//			userService.users.add(new SiteUser("user", "user"));
			productRepository.save(new Product(1L, "cabbage","vegetable", "dfgfdegdefgdfgdeg"));
			productRepository.save(new Product(2L, "apple", "fruit", "gsfdgfdgdfg"));
			productRepository.save(new Product(3L, "cherry", "fruit", "small red thingies"));
			productRepository.save(new Product(4L, "tomato", "vegetable", "weird red sphere"));

			System.out.println(productRepository.findAll());
		};
	}
}
