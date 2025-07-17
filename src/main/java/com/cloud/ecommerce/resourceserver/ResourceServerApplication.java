package com.cloud.ecommerce.resourceserver;

import com.cloud.ecommerce.resourceserver.repository.BrandRepository;
import com.cloud.ecommerce.resourceserver.repository.CategoryRepository;
import com.cloud.ecommerce.resourceserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResourceServerApplication {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BrandRepository brandRepository;

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}
	/*@Bean
	CommandLineRunner seedDatabase() {
		return args -> {
			Brand[] brands = new Brand[] {new Brand("Addidas"), new Brand("Slazenger"), new Brand("Nike") };
			brandRepository.saveAll(Arrays.asList(brands));
			Category[] cats = { new Category("Running"), new Category("Tennis"), new Category("Basketball")  };
			categoryRepository.saveAll(Arrays.asList(cats));
			Product[] products = {
					new Product(new Category(2L), new Brand(3L), "A101","World Star",
							"Shoes for next century", 195.5,"shoe-1.png", true, 12, new Date(2022,8,11),
							new Date(2022, 8,11)),
					new Product(new Category(1L), new Brand(3L), "A102","White Line",
							"Will make you world champion", 295.5,"shoe-2.png", true, 12, new
							Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(3L), new Brand(3L), "A103","Prism White",
							"You have already won a gold medal", 135.5,"shoe-3.png", true, 12, new
							Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(1L), new Brand(1L), "A104","Olympic Runner",
							"Hitect in shoesy", 195.5,"shoe-4.png", true, 12, new Date(2022,8,11), new
							Date(2022, 8,11)),
					new Product(new Category(2L), new Brand(2L), "A105","Lala Land Shoes",
							"Romantism with hitech combined", 185.5,"shoe-5.png", true, 12, new
							Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(3L), new Brand(3L), "A106","Dunker in the Sky",
							"Blue Star  for next century", 195.5,"shoe-6.png", true, 12, new
							Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(1L), new Brand(2L),
							"A107","Even the smallest can dunk", "Bouncy Shoes for next century",
							165.5,"shoe-7.png", true, 12, new Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(2L), new Brand(3L), "A108","Wimbledon Star",
							"Grass or sand don't matter", 167.5,"shoe-8.png", true, 12, new
							Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(3L), new Brand(1L),
							"A109","Will blow your brains out!", "Do something good for your brains",
							175.5,"shoe-9.png", true, 12, new Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(1L), new Brand(3L), "A110","Universal  Star",
							"Neptune Plazma", 105.5,"shoe-10.png", true, 12, new Date(2022,8,11), new
							Date(2022, 8,11)),
					new Product(new Category(2L), new Brand(2L), "A111","Saturn",
							"Will take you to the Saturn", 115.5,"shoe-11.png", true, 12, new
							Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(3L), new Brand(1L), "A112","Paris Blues",
							"Save the environment", 195.5,"shoe-12.png", true, 12, new Date(2022,8,11),
							new Date(2022, 8,11)), new Product(new Category(2L), new Brand(3L),
					"A113","Vegan  Star", "Vegan", 125.5,"shoe-13.png", true, 12, new
					Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(2L), new Brand(2L), "A114","London Star",
							"Piccadily", 145.5,"shoe-14.png", true, 12, new Date(2022,8,11), new
							Date(2022, 8,11)),
					new Product(new Category(1L), new Brand(1L), "A115","Istanbul Star",
							"Bhosphoros Blues", 165.5,"shoe-15.png", true, 12, new Date(2022,8,11), new
							Date(2022, 8,11)),
					new Product(new Category(3L), new Brand(3L), "A116","One and Only",
							"Roland Garros", 155.5,"shoe-16.png", true, 12, new Date(2022,8,11), new
							Date(2022, 8,11)),
					new Product(new Category(1L), new Brand(2L), "A117","NBA Star", "Warriors",
							125.5,"shoe-17.png", true, 12, new Date(2022,8,11), new Date(2022, 8,11)),
					new Product(new Category(2L), new Brand(3L), "A118","Atlantic All Star ",
							"Wimbledon", 191.5,"shoe-18.png", true, 12, new Date(2022,8,11), new
							Date(2022, 8,11)), };
			productRepository.saveAll(Arrays.asList(products));


		};


	}*/


}
