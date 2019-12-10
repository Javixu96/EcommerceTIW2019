package es.uc3m.ecommerce.catalogue.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDAO extends CrudRepository<Category, Integer> {

	Category findByCategoryNameAndIsDeleted(String name, int isDeleted);
	
	@Query("SELECT c FROM Category c WHERE c.categoryId in "
			+ "(SELECT DISTINCT c.categoryId FROM Category c WHERE c.parentId = 1) AND c.parentId IS NOT NULL AND c.isDeleted = 0")
	List<Category> findCategoryParents();

	Category findByCategoryIdAndIsDeleted(int id, int i);

}
