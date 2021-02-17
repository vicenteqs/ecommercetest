package es.vicenteqs.ecommercetest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import es.vicenteqs.ecommercetest.AbstractDBTest;
import es.vicenteqs.ecommercetest.constant.Profiles;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = Profiles.SPRING_PROFILE_TEST)
@AutoConfigureMockMvc
@ContextConfiguration
@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractDBTest {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected ResourceLoader resourceLoader;

	@Value("${app.client.id}")
	private String clientId;

	@Value("${app.client.secret}")
	private String clientSecret;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(this.springSecurityFilterChain).build();
	}

	protected String obtainAccessToken(String username, String password) throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", username);
		params.add("password", password);
		params.add("client_id", this.clientId);
		params.add("client_secret", this.clientSecret);
		params.add("grant_type", "password");
		params.add("scope", "openid");

		ResultActions result = this.mvc
				.perform(post("/oauth/token").contentType(MediaType.MULTIPART_FORM_DATA).params(params))
				.andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getContentAsString();

		return new JacksonJsonParser().parseMap(resultString).get("access_token").toString();
	}

	protected String getBearerHeader(String user, String password) throws Exception {
		final String accessToken = this.obtainAccessToken(user, password);
		return "Bearer " + accessToken;
	}
}
