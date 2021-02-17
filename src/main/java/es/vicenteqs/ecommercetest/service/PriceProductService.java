package es.vicenteqs.ecommercetest.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.vicenteqs.ecommercetest.dto.PriceProductDto;
import es.vicenteqs.ecommercetest.exception.PriceProductNotFoundException;
import es.vicenteqs.ecommercetest.model.domain.PriceProduct;
import es.vicenteqs.ecommercetest.model.repository.PriceProductRepository;

@Service
public class PriceProductService extends BaseService<PriceProduct> {

	@Autowired
	private PriceProductRepository priceProductRepository;

	public PriceProductDto getPriceProductByBrandAndDate(Date date, Long productId, Long brandId)
			throws PriceProductNotFoundException {

		return this.priceProductRepository.findByBrandProductDate(date, productId, brandId, PageRequest.of(0, 1))
				.stream().findFirst().orElseThrow(PriceProductNotFoundException::new);

	}

	public PriceProduct getPriceProductByBrandAndDateNoDto(Date date, Long productId, Long brandId)
			throws PriceProductNotFoundException {
		return this.priceProductRepository.findByBrandProductDateNoDto(date, productId, brandId, PageRequest.of(0, 1))
				.stream().findFirst().orElseThrow(PriceProductNotFoundException::new);
	}

	public PriceProduct getPriceProductByBrandAndDateSpring(Date date, Long productId, Long brandId)
			throws PriceProductNotFoundException {
		return this.priceProductRepository
				.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
						date, date, productId, brandId)
				.orElseThrow(PriceProductNotFoundException::new);
	}
}
