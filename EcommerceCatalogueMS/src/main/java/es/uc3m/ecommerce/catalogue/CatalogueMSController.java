package es.uc3m.ecommerce.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.ecommerce.catalogue.model.Category;
import es.uc3m.ecommerce.catalogue.model.CategoryDAO;

@CrossOrigin
@RestController
public class CatalogueMSController {

	@Autowired
	CategoryDAO categoryDAO;
	
	/*Buscar categoría por id*/
	@RequestMapping(value="/categories/{id}", method= RequestMethod.GET)
	public ResponseEntity<Category> getCategoryById(@PathVariable int id){
		System.out.println("looking for id");
		ResponseEntity<Category> response = null;
		Category cat = categoryDAO.findByCategoryIdAndIsDeleted(id, 0);
		
		if (cat == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cat, HttpStatus.OK);
		}
		return response;
	}
	
	
	@RequestMapping(value="/categories", method= RequestMethod.GET)
	public ResponseEntity<Category> getCategoryByName(@RequestParam(value = "categoryName") String name){
		
		System.out.println("searching: " + name);
		ResponseEntity<Category> response = null;
		Category cat = categoryDAO.findByCategoryNameAndIsDeleted(name, 0);
		
		if (cat == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cat, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/categories/parents", method= RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategoryParents(){
		System.out.println("category parents");
		ResponseEntity<List<Category>> response = null;
		List<Category> parentCategories = categoryDAO.findCategoryParents();
		
		if (parentCategories == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(parentCategories, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public ResponseEntity<Category> newCategory(@RequestBody Category newCategory){
		System.out.println("creating new category");
		ResponseEntity<Category> response = null;
		Category newCat = categoryDAO.save(newCategory);
		
		if (newCat == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(newCat, HttpStatus.CREATED);
		}
		return response;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.PUT)
	public ResponseEntity<Category> updateCategory(@RequestBody Category categoryToUpdate){
		ResponseEntity<Category> response = null;
		Category updatedCategory = categoryDAO.save(categoryToUpdate);	
		
		if (updatedCategory == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(updatedCategory, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable int id){
		System.out.println("updating category " + id);
		ResponseEntity<Category> response = null;
		Category updateCategory = categoryDAO.findByCategoryIdAndIsDeleted(id, 0);
		
		if (updateCategory == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			updateCategory.setIsDeleted(1);
			categoryDAO.save(updateCategory);
			response = new ResponseEntity<>(updateCategory, HttpStatus.OK);
		}
		return response;
	}
}
