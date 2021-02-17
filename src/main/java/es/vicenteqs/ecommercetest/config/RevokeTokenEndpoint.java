package es.vicenteqs.ecommercetest.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevokeTokenEndpoint {

	@Resource
	private ConsumerTokenServices tokenServices;

	@DeleteMapping(value = "/oauth/revoke-token")
	public void revokeToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.contains("Bearer")) {
			String tokenId = authorization.substring("Bearer".length() + 1);
			this.tokenServices.revokeToken(tokenId);
		}
	}
}