package es.uc3m.ecommerce.users.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {

	List<Product> findByAppuserAndIsDeleted(Appuser user, int i);

}
