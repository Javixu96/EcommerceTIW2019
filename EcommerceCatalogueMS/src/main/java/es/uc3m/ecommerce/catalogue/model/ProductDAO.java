package es.uc3m.ecommerce.catalogue.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	
	    @Query("SELECT p FROM Product p WHERE UPPER(p.productName) "
	    		+ " LIKE UPPER(:title) AND p.isDeleted = 0") List<Product> findByName(String title);
	    
	    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
	    		+ " AND p.isDeleted = 0") List<Product> findByPrice(int priceMin, int priceMax);
	    
		@Query("SELECT p FROM Product p WHERE p.categoryBean.categoryId = :category AND p.isDeleted = 0") List<Product> findByCategory(int category);
	    
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) "
				+ "LIKE UPPER(:query) AND p.isDeleted = 0") List<Product> findByDesc(String query);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0") List<Product> findByNameAndCategory(String title, int category);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByNameAndPrice(String title, int priceMin, int priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.isDeleted = 0 AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByNameAndDesc(String title, String query);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) LIKE UPPER(:query) "
				+ " AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByDescAndPrice(String query, int priceMin, int priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.shortDesc) LIKE UPPER(:query) "
				+ " AND p.isDeleted = 0 AND p.categoryBean.categoryId = :category") List<Product> findByDescAndCategory(String query, int category);
		
		@Query("SELECT p FROM Product p WHERE p.categoryBean.categoryId = :category "
				+ " AND p.isDeleted = 0 AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByCategoryAndPrice(int category, int priceMin, int priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ " AND p.price BETWEEN :priceMin AND :priceMax") List<Product> findByNameAndCategoryAndPrice(String title, int category, int priceMin, int priceMax);
		
		@Query("SELECT p FROM Product p WHERE UPPER(p.productName) LIKE UPPER(:title) "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByNameAndCategoryAndDesc(String title, int category, String query);
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
				+ " AND p.categoryBean.categoryId = :category AND p.isDeleted = 0"
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndCategoryAndDesc(int priceMin, int priceMax, int category, String query);
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax "
				+ " AND UPPER(p.productName) LIKE UPPER(:title) AND p.isDeleted = 0"
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndNameAndDesc(int priceMin, int priceMax, String title, String query);		
		
		@Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceMin AND :priceMax AND UPPER(p.productName) LIKE UPPER(:title) AND p.isDeleted = 0"
				+ " AND p.categoryBean.categoryId = :category "
				+ " AND UPPER(p.shortDesc) LIKE UPPER(:query)") List<Product> findByPriceAndNameAndDescAndCategory(int priceMin, int priceMax, String title, int category, String query);
		
		
}
