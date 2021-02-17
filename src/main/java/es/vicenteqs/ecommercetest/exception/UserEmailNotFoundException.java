package es.vicenteqs.ecommercetest.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import es.vicenteqs.ecommercetest.constant.ExceptionCodes;

public class UserEmailNotFoundException extends UsernameNotFoundException {

	private static final long serialVersionUID = -8497175331056546416L;

	public UserEmailNotFoundException() {
		super(ExceptionCodes.USER_EMAIL_NOT_FOUND);
	}

}
