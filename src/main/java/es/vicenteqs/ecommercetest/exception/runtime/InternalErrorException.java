package es.vicenteqs.ecommercetest.exception.runtime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends RuntimeException {

	private static final long serialVersionUID = 9038839800473800236L;

	public InternalErrorException(String message) {
		super(message);
	}
}