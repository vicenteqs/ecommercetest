package es.vicenteqs.ecommercetest.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public abstract class CustomException extends Exception {

	private static final long serialVersionUID = 6033238319770940691L;

	@Getter
	private final String code;

	public CustomException(String code) {
		this.code = code;
	}

	public HttpStatus getStatusCode() {
		return HttpStatus.BAD_REQUEST;
	}
}
