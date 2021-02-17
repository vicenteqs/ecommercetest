package es.vicenteqs.ecommercetest.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.vicenteqs.ecommercetest.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageUtils messageUtils;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		GlobalExceptionHandler.log.error("handleMethodArgumentNotValid", ex);

		ApiError apiError = new ApiError();

		apiError.setStatus(status);
		apiError.setOrigin(request.getDescription(false));

		apiError.setErrors(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList()));

		apiError.setErrorFields(
				ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));

		return new ResponseEntity<>(apiError, headers, status);

	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		GlobalExceptionHandler.log.error("handleMissingServletRequestParameter", ex);

		ApiError apiError = new ApiError();

		apiError.setStatus(status);
		apiError.setOrigin(request.getDescription(false));

		apiError.setErrors(Arrays.asList(ex.getLocalizedMessage()));

		return new ResponseEntity<>(apiError, headers, status);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		GlobalExceptionHandler.log.error("handleHttpMessageNotReadable", ex);

		ApiError apiError = new ApiError(status, request.getDescription(false), ex.getLocalizedMessage());

		return new ResponseEntity<>(apiError, headers, status);

	}

	@ExceptionHandler(value = { CustomException.class })
	protected ResponseEntity<Object> handleConflict(CustomException ex, WebRequest request) {
		ApiError apiError = new ApiError(ex.getStatusCode(), request.getDescription(false),
				this.messageUtils.getExceptionMessage(ex.getCode()), ex.getCode());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	protected ResponseEntity<Object> handleGenericConflict(Exception ex, WebRequest request) {

		GlobalExceptionHandler.log.error("handleGenericConflict", ex);

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request.getDescription(false),
				ex.getLocalizedMessage());

		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}