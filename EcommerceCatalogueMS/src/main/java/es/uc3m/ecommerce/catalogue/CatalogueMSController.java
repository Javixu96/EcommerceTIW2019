package es.uc3m.ecommerce.catalogue;

import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.uc3m.ecommerce.catalogue.model.Product;
import es.uc3m.ecommerce.catalogue.model.ProductDAO;
import es.uc3m.ecommerce.catalogue.model.Category;
import es.uc3m.ecommerce.catalogue.model.CategoryDAO;

@CrossOrigin
@RestController
@Controller
public class CatalogueMSController {

	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	//PRODUCT - LLAMADAS GET
	
	// buscar producto por su ID
	@RequestMapping(value="/products/{id}", method= RequestMethod.GET) 
	public ResponseEntity<Product> findProductById(@PathVariable int id){
		
		ResponseEntity<Product> response = null;
		Product p = productDAO.findById(id).orElse(null);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar producto según sus atributos descripcion, precio, nombre y categoria
	@RequestMapping(value="/products", method= RequestMethod.GET) // buscar todos los productos filtrados por campos
	public ResponseEntity<List<Product>> findByAttributes(
			@RequestParam(value="productName", required = false) String productName,
			@RequestParam(value="shortDesc", required = false) String shortDesc,
			@RequestParam(value="category", required = false) Integer categoryId, 
			@RequestParam(value="priceMin", required = false) Integer priceMin,
			@RequestParam(value="priceMax", required = false) Integer priceMax){
		
		ResponseEntity<List<Product>> response = null;
		List<Product> pList = null; 
		
		
		pList = (List<Product>) productDAO.searchProducts(priceMin, priceMax, productName, categoryId, shortDesc);
		
		System.out.println(pList.size());
		// COMPROBAR SI LA BUSQUEDA A RETORNADO RESULTADOS
		
		response = new ResponseEntity<>(pList, HttpStatus.OK);
		
		return response;
	}
	
	// PRODUCT - LLAMADAS POST
	// insertar un nuevo producto
	@RequestMapping(value="/products", method= RequestMethod.POST) 
	public ResponseEntity<Product> insertProduct(@RequestBody @Validated Product p){
		
		ResponseEntity<Product> response = null;
		productDAO.save(p);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(p, HttpStatus.CREATED);
		}
		return response;
	}
	
	// PRODUCT - LLAMADAS PUT
	// Editar un producto existente por si ID, dado otro objeto con los nuevos atributos deseados
	@RequestMapping(value="/products", method= RequestMethod.PUT) 
	public ResponseEntity<Product> modifyProductById(@RequestBody @Validated Product product){
		
		ResponseEntity<Product> response = null;
		
		if (product == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {	
			productDAO.save(product);
			response = new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		return response;
	}
	
	// PRODUCT - LLAMADAS DELETE
	// Borrar producto por su ID
	@RequestMapping(value="/products/{id}", method= RequestMethod.DELETE) // eliminar un producto (editar el flag isDeleted)
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
		
		ResponseEntity<Product> response = null;
		Product p = productDAO.findById(id).orElse(null);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			p.setIsDeleted(1);
			
			productDAO.save(p);
			
			response = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return response;
	}
	
	

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
	public ResponseEntity<List<Category>> getCategory(@RequestParam(value = "categoryName", required = false) String categoryName){
		ResponseEntity<List<Category>> response = null;
		List<Category> cat = categoryName == null ? categoryDAO.findCategoryParents() : categoryDAO.findByCategoryNameAndIsDeleted(categoryName, 0);
		if (cat == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cat, HttpStatus.OK);
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
