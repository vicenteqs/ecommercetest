package es.vicenteqs.ecommercetest.enums;

import lombok.Getter;

public enum Currency {

	EUR("€", "currency.code.euro"), DOLLAR("$", "currency.code.dollar");

	@Getter
	private String sign;

	@Getter
	private String code;

	private Currency(String sign, String code) {
		this.sign = sign;
		this.code = code;
	}

	public boolean isEuro() {
		return this.equals(Currency.EUR);
	}

	public boolean isDollar() {
		return this.equals(Currency.DOLLAR);
	}

}
