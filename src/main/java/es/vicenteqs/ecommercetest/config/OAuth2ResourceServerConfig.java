package es.vicenteqs.ecommercetest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.headers().cacheControl().disable().and().cors().disable().authorizeRequests().antMatchers("/oauth/**",
				"/swagger-ui.html", "/v2/api-docs", "/webjars/springfox-swagger-ui/**", "/swagger-resources/**")
				.permitAll().anyRequest().authenticated();
	}

}