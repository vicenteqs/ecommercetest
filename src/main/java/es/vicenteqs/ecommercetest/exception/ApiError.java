package es.vicenteqs.ecommercetest.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError implements Serializable {

	private static final long serialVersionUID = -7665406786690227401L;

	private HttpStatus status;
	private String origin;
	private Date date;
	private String code;
	private List<String> errors;
	private List<String> errorFields;

	public ApiError() {
		this.errors = new ArrayList<>();
		this.errorFields = new ArrayList<>();
		this.date = new Date();
	}

	public ApiError(HttpStatus status, String origin, List<String> error) {
		this.status = status;
		this.origin = origin;
		this.errors = error;
		this.date = new Date();
	}

	public ApiError(HttpStatus status, String origin) {
		this.status = status;
		this.origin = origin;
		this.errors = new ArrayList<>();
		this.date = new Date();
	}

	public ApiError(HttpStatus status, String origin, String error) {
		this.status = status;
		this.origin = origin;
		this.errors = new ArrayList<>();
		this.addError(error);
		this.date = new Date();
	}

	public ApiError(HttpStatus status, String origin, String error, String code) {
		this.status = status;
		this.origin = origin;
		this.errors = new ArrayList<>();
		this.addError(error);
		this.date = new Date();
		this.code = code;

	}

	public void addError(String error) {
		this.errors.add(error);
	}

	public void addErrorFeilds(String errorField) {
		this.errorFields.add(errorField);
	}
}
