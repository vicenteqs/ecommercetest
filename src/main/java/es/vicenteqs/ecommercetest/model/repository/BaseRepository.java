package es.vicenteqs.ecommercetest.model.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.vicenteqs.ecommercetest.model.domain.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity>
		extends PagingAndSortingRepository<T, Integer>, JpaSpecificationExecutor<T> {
}
