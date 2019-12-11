package es.uc3m.ecommerce.catalogue.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
				+ " AND (UPPER(p.productName) LIKE UPPER(:title) OR :title IS NULL) AND p.isDeleted = 0"
				+ " AND (p.categoryBean.categoryId = :category OR :category IS NULL) "
				+ " AND (UPPER(p.shortDesc) LIKE UPPER(:query) OR :query IS NULL)") List<Product> searchProducts(Integer priceMin, Integer priceMax, String title, Integer category, String query);	

}