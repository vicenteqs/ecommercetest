package es.vicenteqs.ecommercetest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.vicenteqs.ecommercetest.dto.PriceProductDto;
import es.vicenteqs.ecommercetest.model.domain.PriceProduct;

@Mapper(componentModel = "spring")
public interface PriceProductMapper {

	@Mapping(source="id", target="priceList")
	@Mapping(source="brand.id", target="brandId")
	@Mapping(source="product.id", target="productId")
	PriceProductDto entityToDto(PriceProduct priceProduct);

	PriceProduct dtoToEntity(PriceProductDto priceProductDto);

	Iterable<PriceProductDto> entityToDto(Iterable<PriceProduct> priceProduct);

	Iterable<PriceProduct> dtoToEntity(Iterable<PriceProductDto> priceProductDto);

}
