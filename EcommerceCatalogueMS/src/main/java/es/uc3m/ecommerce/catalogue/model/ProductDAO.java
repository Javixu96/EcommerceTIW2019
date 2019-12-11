package es.uc3m.ecommerce.catalogue.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	
	    
	    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
	    		+ " AND p.isDeleted = 0") List<Product> findByPrice(Integer priceMin, Integer priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0") List<Product> findByNameAndCategory(String title, Integer category);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByNameAndPrice(String title, Integer priceMin, Integer priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.isDeleted = 0 AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByNameAndDesc(String title, String query);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) LIKE UPPER(:query) "
				+ " AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByDescAndPrice(String query, Integer priceMin, Integer priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) LIKE UPPER(:query) "
				+ " AND p.isDeleted = 0 AND p.categoryBean.categoryId = :category") List<Product> findByDescAndCategory(String query, Integer category);
		
		@Query("SELECT p FROM Product p WHERE p.categoryBean.categoryId = :category "
				+ " AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByCategoryAndPrice(Integer category, Integer priceMin, Integer priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ " AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByNameAndCategoryAndPrice(String title, Integer category, Integer priceMin, Integer priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByNameAndCategoryAndDesc(String title, Integer category, String query);
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndCategoryAndDesc(Integer priceMin, Integer priceMax, Integer category, String query);
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
				+ " AND UPPER(p.productName) LIKE UPPER(:title) AND p.isDeleted = 0"
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndNameAndDesc(Integer priceMin, Integer priceMax, String title, String query);		
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax AND UPPER(p.productName) LIKE UPPER(:title) AND p.isDeleted = 0"
				+ " AND p.categoryBean.categoryId = :category "
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndNameAndDescAndCategory(Integer priceMin, Integer priceMax, String title, Integer category, String query);
		
		
}
