package es.vicenteqs.ecommercetest.model.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Brand extends BaseEntity {

	private static final long serialVersionUID = 4296777538119486544L;
	
	private String name;
	
}
