package es.vicenteqs.ecommercetest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.vicenteqs.ecommercetest.config.Constants;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ProductControllerTest extends AbstractControllerTest {

	private final String endpoint = "/product/35455/price";

	@Test
	public void request1Test() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get(this.endpoint)
				.header(Constants.AUTHORIZATION_HEADER,
						this.getBearerHeader(Constants.TEST_USER, Constants.GENERIC_PASSWORD))
				.accept(MediaType.APPLICATION_JSON_VALUE).param("date", "2020-06-14T10:00:00").param("brandId", "1"))
				.andExpect(status().isOk()).andExpect(content()
						.json(this.jsonResourceAsString(Constants.PRICE_PRODUCT_FOLDER + "/test1_response.json")));
	}

	@Test
	public void request2Test() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get(this.endpoint)
				.header(Constants.AUTHORIZATION_HEADER,
						this.getBearerHeader(Constants.TEST_USER, Constants.GENERIC_PASSWORD))
				.accept(MediaType.APPLICATION_JSON_VALUE).param("date", "2020-06-14T16:00:00").param("brandId", "1"))
				.andExpect(status().isOk()).andExpect(content()
						.json(this.jsonResourceAsString(Constants.PRICE_PRODUCT_FOLDER + "/test2_response.json")));
	}

	@Test
	public void request3Test() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get(this.endpoint)
				.header(Constants.AUTHORIZATION_HEADER,
						this.getBearerHeader(Constants.TEST_USER, Constants.GENERIC_PASSWORD))
				.accept(MediaType.APPLICATION_JSON_VALUE).param("date", "2020-06-14T21:00:00").param("brandId", "1"))
				.andExpect(status().isOk()).andExpect(content()
						.json(this.jsonResourceAsString(Constants.PRICE_PRODUCT_FOLDER + "/test3_response.json")));
	}

	@Test
	public void request4Test() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get(this.endpoint)
				.header(Constants.AUTHORIZATION_HEADER,
						this.getBearerHeader(Constants.TEST_USER, Constants.GENERIC_PASSWORD))
				.accept(MediaType.APPLICATION_JSON_VALUE).param("date", "2020-06-15T10:00:00").param("brandId", "1"))
				.andExpect(status().isOk()).andExpect(content()
						.json(this.jsonResourceAsString(Constants.PRICE_PRODUCT_FOLDER + "/test4_response.json")));
	}

	@Test
	public void request5Test() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get(this.endpoint)
				.header(Constants.AUTHORIZATION_HEADER,
						this.getBearerHeader(Constants.TEST_USER, Constants.GENERIC_PASSWORD))
				.accept(MediaType.APPLICATION_JSON_VALUE).param("date", "2020-06-16T21:00:00").param("brandId", "1"))
				.andExpect(status().isOk()).andExpect(content()
						.json(this.jsonResourceAsString(Constants.PRICE_PRODUCT_FOLDER + "/test5_response.json")));
	}

}
