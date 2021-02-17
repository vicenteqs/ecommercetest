package es.vicenteqs.ecommercetest.model.repository;

import java.util.Optional;

import es.vicenteqs.ecommercetest.model.domain.User;

public interface UserRepository extends BaseRepository<User> {

	Optional<User> findByEmail(String email);

}
