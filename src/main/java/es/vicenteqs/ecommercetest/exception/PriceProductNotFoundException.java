package es.vicenteqs.ecommercetest.exception;

import org.springframework.http.HttpStatus;

import es.vicenteqs.ecommercetest.constant.ExceptionCodes;

public class PriceProductNotFoundException extends CustomException {

	private static final long serialVersionUID = -175836295626220456L;

	public PriceProductNotFoundException() {
		super(ExceptionCodes.PRICE_PRODUCT_NOT_FOUND);
	}

	@Override
	public HttpStatus getStatusCode() {
		return HttpStatus.NOT_FOUND;
	}

}
