package es.vicenteqs.ecommercetest.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import es.vicenteqs.ecommercetest.dto.PriceProductDto;
import es.vicenteqs.ecommercetest.model.domain.PriceProduct;

public interface PriceProductRepository extends BaseRepository<PriceProduct> {

	@Query("select new es.vicenteqs.ecommercetest.dto.PriceProductDto(pp.id, b.id, p.id, pp.startDate, pp.endDate, pp.priority, pp.price, pp.currency) "
			+ "from PriceProduct pp " + "join pp.product p " + "join pp.brand b "
			+ "where pp.startDate <= ?1 and pp.endDate >= ?1 and pp.product.id = ?2 and pp.brand.id = ?3 "
			+ "order by pp.priority desc")
	List<PriceProductDto> findByBrandProductDate(Date date, Long productId, Long brandId, Pageable pageable);

	@Query("select pp " + "from PriceProduct pp "
			+ "where pp.startDate <= ?1 and pp.endDate >= ?1 and pp.product.id = ?2 and pp.brand.id = ?3 "
			+ "order by priority desc")
	List<PriceProduct> findByBrandProductDateNoDto(Date date, Long productId, Long brandId, Pageable pageable);

	Optional<PriceProduct> findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
			Date start, Date end, Long productId, Long brandId);

}
