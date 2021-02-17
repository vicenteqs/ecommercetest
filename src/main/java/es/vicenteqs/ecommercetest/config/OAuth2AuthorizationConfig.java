package es.vicenteqs.ecommercetest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserDetailsService credentialsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Value("${app.client.id}")
	private String clientId;

	@Value("${app.client.secret}")
	private String clientSecret;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(this.clientId).secret(this.passwordEncoder.encode(this.clientSecret))
				.authorizedGrantTypes("authorization_code", "refresh_token", "password").resourceIds("oauth2-resource")
				.scopes("openid");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.approvalStore(new InMemoryApprovalStore()).authenticationManager(this.authManager)
				.authorizationCodeServices(new InMemoryAuthorizationCodeServices())
				.userDetailsService(this.credentialsService).tokenServices(this.tokenServices());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenService = new DefaultTokenServices();
		tokenService.setTokenStore(this.tokenStore());
		tokenService.setSupportRefreshToken(true);
		return tokenService;
	}
	
	@Bean
	public TokenStore tokenStore() {
	    return new InMemoryTokenStore();
	}
}