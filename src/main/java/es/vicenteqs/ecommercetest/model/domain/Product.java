package es.vicenteqs.ecommercetest.model.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

	private static final long serialVersionUID = -2455339918263634596L;

	private String name;

}
