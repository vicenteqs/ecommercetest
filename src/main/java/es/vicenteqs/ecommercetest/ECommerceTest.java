package es.vicenteqs.ecommercetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ECommerceTest extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ECommerceTest.class);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(ECommerceTest.class, args);
	}

}
