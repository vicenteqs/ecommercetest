package es.vicenteqs.ecommercetest.model.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.vicenteqs.ecommercetest.enums.Currency;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PriceProduct extends BaseEntity {

	private static final long serialVersionUID = -6067368471114100903L;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Brand brand;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private BigDecimal price;

	private int priority;

	@Enumerated(EnumType.STRING)
	private Currency currency;

}
