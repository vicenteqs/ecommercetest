package es.vicenteqs.ecommercetest.exception;

import es.vicenteqs.ecommercetest.constant.ExceptionCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterOperationNotSupportedException extends CustomException {

	private static final long serialVersionUID = -3012874135668676821L;

	public FilterOperationNotSupportedException() {
		super(ExceptionCodes.FILTER_OPERATION_NOT_SUPPORTED);
	}

}
