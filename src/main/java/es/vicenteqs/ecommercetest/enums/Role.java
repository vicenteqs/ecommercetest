package es.vicenteqs.ecommercetest.enums;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

public enum Role implements GrantedAuthority {

	USER("role.code.admin"), ADMIN("role.code.buyer");

	@Getter
	private String code;

	private Role(String code) {
		this.code = code;
	}

	@Override
	public String getAuthority() {
		return this.name();
	}

	public boolean isAdmin() {
		return this.equals(Role.ADMIN);
	}
	
	public boolean isUser() {
		return this.equals(Role.USER);
	}

}
