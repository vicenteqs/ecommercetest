package es.vicenteqs.ecommercetest.exception.runtime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.vicenteqs.ecommercetest.constant.ExceptionCodes;
import es.vicenteqs.ecommercetest.exception.CustomException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends CustomException {

	private static final long serialVersionUID = -2569425976387370486L;

	public ElementNotFoundException() {
		super(ExceptionCodes.ELEMENT_NOT_FOUND);
	}
}