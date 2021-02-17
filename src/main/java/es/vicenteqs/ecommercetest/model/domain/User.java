package es.vicenteqs.ecommercetest.model.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.vicenteqs.ecommercetest.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity implements UserDetails {

	protected static final long serialVersionUID = 1947933799383566488L;

	@Enumerated(EnumType.STRING)
	protected Role role;

	@Column(unique = true)
	protected String email;

	@JsonIgnore
	protected String password;

	protected boolean accountNonExpired;

	protected boolean accountNonLocked;

	protected boolean credentialsNonExpired;

	protected boolean enabled;

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(this.role);
	}

}
