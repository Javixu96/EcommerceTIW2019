package es.uc3m.ecommerce.catalogue.model;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	
}
