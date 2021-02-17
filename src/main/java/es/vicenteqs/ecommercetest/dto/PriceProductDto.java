package es.vicenteqs.ecommercetest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import es.vicenteqs.ecommercetest.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceProductDto implements Serializable {

	private static final long serialVersionUID = 5348695955844710369L;

	private Long priceList;
	private Long brandId;
	private Long productId;
	private Date startDate;
	private Date endDate;
	private int priority;
	private BigDecimal price;

	private Currency currency;

}
