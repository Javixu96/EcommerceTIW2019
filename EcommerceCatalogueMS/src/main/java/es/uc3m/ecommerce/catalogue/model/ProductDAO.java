package es.uc3m.ecommerce.catalogue.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	
		@Query("SELECT p FROM Product p WHERE (:priceMin is NULL OR p.price> :priceMin)"
				+ " AND (:priceMax is NULL OR p.price< :priceMax)  "
				+ " AND (:title IS NULL OR UPPER(p.productName) LIKE CONCAT('%',UPPER(:title),'%') ) "
				+ " AND p.isDeleted = 0 "
				+ " AND (:category IS NULL OR p.categoryBean.categoryId = :category ) "
				+ " AND (:query IS NULL OR UPPER(p.shortDesc) LIKE CONCAT('%',UPPER(:query),'%'))")
		List<Product> searchProducts(Integer priceMin, Integer priceMax, String title, Integer category, String query);	

}