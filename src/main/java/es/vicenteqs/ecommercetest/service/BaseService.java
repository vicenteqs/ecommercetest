package es.vicenteqs.ecommercetest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import es.vicenteqs.ecommercetest.exception.runtime.ElementNotFoundException;
import es.vicenteqs.ecommercetest.exception.runtime.PreconditionFailedException;
import es.vicenteqs.ecommercetest.model.domain.BaseEntity;
import es.vicenteqs.ecommercetest.model.repository.BaseRepository;
import es.vicenteqs.ecommercetest.model.specification.GenericSpecification;
import es.vicenteqs.ecommercetest.util.MessageUtils;

@Service
public abstract class BaseService<T extends BaseEntity> {

	@Autowired
	protected MessageUtils msgUtils;

	@Autowired
	protected BaseRepository<T> baseRepository;

	public void checkById(Integer id) throws PreconditionFailedException, ElementNotFoundException {
		if (id == null) {
			throw new PreconditionFailedException();
		}

		if (!this.baseRepository.existsById(id)) {
			throw new ElementNotFoundException();
		}
	}

	public T findById(Integer id) throws PreconditionFailedException, ElementNotFoundException {

		if (id == null) {
			throw new PreconditionFailedException();
		}

		Optional<T> optional = this.baseRepository.findById(id);

		if (!optional.isPresent()) {
			throw new ElementNotFoundException();
		}

		return optional.get();

	}

	public T save(T entity) {
		return this.baseRepository.save(entity);
	}

	public Iterable<T> saveAll(Iterable<T> entities) {
		return this.baseRepository.saveAll(entities);
	}

	public Iterable<T> getAll() {
		return this.baseRepository.findAll();
	}

	public Iterable<T> getAll(String filter, Integer page, Integer size) {
		Pageable pageable = null;
		Specification<T> spec = new GenericSpecification<>();
		Iterable<T> list = null;

		if (page != null && size != null) {
			pageable = PageRequest.of(page, size);
		}

		if (filter != null) {
			spec = new GenericSpecification<>(filter);
		}

		if (pageable != null) {
			list = this.baseRepository.findAll(spec, pageable);
		} else {
			list = this.baseRepository.findAll(spec);
		}

		return list;
	}

	public void deleteById(Integer id) {
		this.baseRepository.deleteById(id);
	}

}
