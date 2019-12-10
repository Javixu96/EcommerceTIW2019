package es.uc3m.ecommerce.catalogue.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	
	    @Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
	    		+ "LIKE UPPER(:title) AND p.isDeleted = 0") List<Product> findByName();
	    
	    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
	    		+ "p.isDeleted = 0") List<Product> findByPrice();
	    
		@Query("SELECT p FROM Product p WHERE p.categoryBean.categoryId = :category AND p.isDeleted = 0") List<Product> findByCategory();
	    
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) "
				+ "LIKE UPPER(:query) AND p.isDeleted = 0") List<Product> findByDesc();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
				+ "LIKE UPPER(:title) AND p.categoryBean.categoryId = :category AND p.isDeleted = 0") List<Product> findByNameAndCategory();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
				+ "LIKE UPPER(:title) AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByNameAndPrice();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
				+ "LIKE UPPER(:title) AND p.isDeleted = 0 AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByNameAndDesc();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) "
				+ "LIKE UPPER(:query) AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByDescAndPrice();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) "
				+ "LIKE UPPER(:query) AND p.isDeleted = 0 AND p.categoryBean.categoryId = :category") List<Product> findByDescAndCategory();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.categoryBean.categoryName) "
				+ "LIKE UPPER(:query) AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByCategoryAndPrice();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
				+ "LIKE UPPER(:title) AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ "AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByNameAndCategoryAndPrice();
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
				+ "LIKE UPPER(:title) AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ "AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByNameAndCategoryAndDesc();
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax"
				+ "LIKE UPPER(:title) AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ "AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndCategoryAndDesc();
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax"
				+ "LIKE UPPER(:title) AND WHERE UPPER(p.productName) LIKE UPPER(:title) AND p.isDeleted = 0"
				+ "AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndNameAndDesc();		
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax"
				+ "LIKE UPPER(:title) AND WHERE UPPER(p.productName) LIKE UPPER(:title) AND p.isDeleted = 0"
				+ "AND p.categoryBean.categoryId = :category "
				+ "AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndNameAndDescAndCategory();
		
		
}
