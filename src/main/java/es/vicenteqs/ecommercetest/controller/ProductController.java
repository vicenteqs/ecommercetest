package es.vicenteqs.ecommercetest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.vicenteqs.ecommercetest.constant.Constants;
import es.vicenteqs.ecommercetest.dto.PriceProductDto;
import es.vicenteqs.ecommercetest.exception.PriceProductNotFoundException;
import es.vicenteqs.ecommercetest.mapper.PriceProductMapper;
import es.vicenteqs.ecommercetest.service.PriceProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private PriceProductService productService;

	@Autowired
	private PriceProductMapper priceProductMapper;

	@GetMapping("/{id}/price")
	public PriceProductDto getBiddingWithFilter(
			@RequestParam(required = true) @DateTimeFormat(pattern = Constants.DATETIME_FORMAT) Date date,
			@PathVariable Long id, @RequestParam(required = true) Long brandId) throws PriceProductNotFoundException {
		return this.productService.getPriceProductByBrandAndDate(date, id, brandId);
	}

	@GetMapping("/{id}/price-mapper")
	public PriceProductDto getBiddingWithFilterMapper(
			@RequestParam(required = true) @DateTimeFormat(pattern = Constants.DATETIME_FORMAT) Date date,
			@PathVariable Long id, @RequestParam(required = true) Long brandId) throws PriceProductNotFoundException {
		return this.priceProductMapper
				.entityToDto(this.productService.getPriceProductByBrandAndDateNoDto(date, id, brandId));
	}

	@GetMapping("/{id}/price-spring")
	public PriceProductDto getBiddingWithFilterSpringJpa(
			@RequestParam(required = true) @DateTimeFormat(pattern = Constants.DATETIME_FORMAT) Date date,
			@PathVariable Long id, @RequestParam(required = true) Long brandId) throws PriceProductNotFoundException {
		return this.priceProductMapper
				.entityToDto(this.productService.getPriceProductByBrandAndDateSpring(date, id, brandId));
	}

}
