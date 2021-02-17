package es.vicenteqs.ecommercetest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.vicenteqs.ecommercetest.model.repository.UserRepository;
import es.vicenteqs.ecommercetest.util.DateUtils;

@Transactional
public abstract class AbstractDBTest extends AbstractTest {

	@MockBean
	protected DateUtils dateUtils;

	@Autowired
	protected UserRepository userRepository;



}
