package es.vicenteqs.ecommercetest.exception.runtime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 9038839800473800236L;

	public BadRequestException() {

	}

	public BadRequestException(String message) {
		super(message);
	}

}